package org.quemepongo.models;

public record Sugerencia(Prenda superior, Prenda inferior, Prenda calzado) {

  public boolean esAptaTemperatura(Temperatura temperaturaActual) {
    return superior.esApta(temperaturaActual) && inferior.esApta(temperaturaActual) && calzado().esApta(temperaturaActual);
  }
}
