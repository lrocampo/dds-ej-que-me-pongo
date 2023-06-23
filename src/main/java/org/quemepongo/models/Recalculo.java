package org.quemepongo.models;

import java.util.List;

public class Recalculo implements Accion {
  @Override
  public void activar(Usuario usuario, List<Alerta> alertas) {
    usuario.getSugerenciaDiaria();
  }
}