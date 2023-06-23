package org.quemepongo.models;

import java.util.List;

public interface Accion {
  void activar(Usuario usuario, List<Alerta> alertas);
}
