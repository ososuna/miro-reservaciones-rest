package dev.ososuna.miro.model.enums;

public enum Role {

  ROLE_ADMIN("ROLE_ADMIN"),
  ROLE_USER("ROLE_USER");

  private final String text;

  Role(final String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return text;
  }

}
