package org.quemepongo.models;

public interface MailSender {
  void send(String mail, String mensaje);
}