package dev.ososuna.miro.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;

import dev.ososuna.miro.model.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="resident")
public class Resident extends AbstractModificationAttributesEntity implements UserDetails {
  
  @Column(name="first_name", nullable=false)
  private String firstName;

  @Column(name="last_name", nullable=false)
  private String lastName;

  @Column(name="email", nullable=false)
  private String email;
  
  @Column(name="password", nullable=false)
  private String password;

  @Column(name="role", nullable=false)
  @Enumerated(EnumType.STRING)
  private Role role;

  @JoinColumn(name="section_id", nullable=false)
  @ManyToOne
  private Section section;

  @JsonBackReference(value="reservations")
  @OneToMany(mappedBy="resident")
  private List<Reservation> reservations;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
