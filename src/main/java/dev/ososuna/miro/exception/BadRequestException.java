package dev.ososuna.miro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class BadRequestException extends MiroException {

  public BadRequestException(String message) {
    super(message);
  }

}
