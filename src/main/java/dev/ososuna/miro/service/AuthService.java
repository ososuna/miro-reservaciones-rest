package dev.ososuna.miro.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.ososuna.miro.configuration.PropertiesConfig;
import dev.ososuna.miro.exception.BadRequestException;
import dev.ososuna.miro.exception.NotFoundException;
import dev.ososuna.miro.model.Resident;
import dev.ososuna.miro.model.dto.AuthResponseDto;
import dev.ososuna.miro.model.dto.LoginRequestDto;
import dev.ososuna.miro.model.dto.RegisterRequestDto;
import dev.ososuna.miro.model.enums.Role;
import dev.ososuna.miro.repository.ResidentRepository;
import dev.ososuna.miro.util.JwtUtil;
import dev.ososuna.miro.util.ResidentUtil;
import dev.ososuna.miro.util.SectionUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final AuthenticationManager authenticationManager;
  private final PasswordEncoder passwordEncoder;
  private final PropertiesConfig propertiesConfig;
  private final JwtUtil jwtUtil;
  private final ResidentUtil residentUtil;
  private final ResidentRepository residentRepository;
  private final SectionUtil sectionUtil;

  public AuthResponseDto login(LoginRequestDto request) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    var resident = residentRepository.findByEmailAndActiveTrue(request.getEmail()).orElseThrow();
    var jwt = jwtUtil.generateToken(resident, propertiesConfig.getJwtAccessExpirationMs());
    return AuthResponseDto.builder()
      .token(jwt)
      .resident(residentUtil.transformResidentToResidentDto(resident))
      .build();
  }

  public AuthResponseDto register(RegisterRequestDto request) throws BadRequestException, NotFoundException {
    if (request.getPassword().length() < 6) {
      throw new BadRequestException("Password must be at least 6 characters long");
    }
    if (residentRepository.existsByEmailAndActiveTrue(request.getEmail())) {
      throw new BadRequestException("Email already exists");
    }

    var resident = Resident.builder()
      .firstName(request.getFirstName())
      .lastName(request.getLastName())
      .email(request.getEmail())
      .password(passwordEncoder.encode(request.getPassword()))
      .section(sectionUtil.getSectionById(request.getSection()))
      .role(Role.ROLE_USER)
      .build();
    resident.setActive(true);
    var savedUser = residentRepository.save(resident);
    var jwt = jwtUtil.generateToken(resident, propertiesConfig.getJwtAccessExpirationMs());
    return AuthResponseDto.builder()
      .token(jwt)
      .resident(residentUtil.transformResidentToResidentDto(savedUser))
      .build();
  }

  public AuthResponseDto refreshToken(String token) throws NotFoundException, BadRequestException {
    try {
      var email = jwtUtil.extractUsername(token);
      var resident = residentUtil.getResidentByEmail(email);
      if (!jwtUtil.isTokenValid(token, resident)) {
        token = jwtUtil.generateToken(resident, propertiesConfig.getJwtAccessExpirationMs());
      }
      return AuthResponseDto.builder()
        .token(token)
        .resident(residentUtil.transformResidentToResidentDto(resident))
        .build();
    } catch (ExpiredJwtException e) {
      throw new BadRequestException(e.getMessage());
    }
   
  }

  public Boolean verifyToken(String token) throws NotFoundException, BadRequestException {
    var email = jwtUtil.extractUsername(token);
    var resident = residentUtil.getResidentByEmail(email);
    return jwtUtil.isTokenValid(token, resident);
  }

}
