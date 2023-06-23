package org.quemepongo.models;

import java.util.List;

public class EnviarNotificacionApp implements Accion {
  NotificationService notificationService;

  @Override
  public void activar(Usuario usuario, List<Alerta> alertas) {
    alertas.forEach(alerta ->  notificationService.notify(alerta.mensaje()));
  }
}
