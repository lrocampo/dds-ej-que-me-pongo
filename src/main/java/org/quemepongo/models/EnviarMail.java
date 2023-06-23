package org.quemepongo.models;

import java.util.List;

public class EnviarMail implements Accion {
  MailSender mailSender;

  @Override
  public void activar(Usuario usuario, List<Alerta> alertas) {
    alertas.forEach(alerta ->  mailSender.send(usuario.getMail(), alerta.name()));
  }
}
