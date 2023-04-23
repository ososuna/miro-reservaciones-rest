package dev.ososuna.miro.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import dev.ososuna.miro.model.AbstractModificationAttributesEntity;
import dev.ososuna.miro.model.Resident;

@Component
public class SessionUtil {
  public Long getLoggedUserId() {
    var context = (Resident) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return context.getId();
  }

  public void setCreatedBy(AbstractModificationAttributesEntity entity) {
    entity.setCreatedBy(getLoggedUserId());
  }

  public void setUpdatedBy(AbstractModificationAttributesEntity entity) {
    entity.setUpdatedBy(getLoggedUserId());
  }
}
