package org.quemepongo.models;

import java.util.HashSet;
import java.util.Set;

public class Guardarropas {
  private Set<Prenda> prendas = new HashSet<>();

  public void cargarPrenda(Prenda prenda) {
    prendas.add(prenda);
  }

}
