package org.quemepongo.models;

public class CondicionClimatica {
  private double probabilidadPrecipitacion;
  private Temperatura temperatura;

  public CondicionClimatica(double probabilidadPrecipitacion, Temperatura temperatura) {
    this.probabilidadPrecipitacion = probabilidadPrecipitacion;
    this.temperatura = temperatura;
  }

  public double getProbabilidadPrecipitacion() {
    return probabilidadPrecipitacion;
  }

  public Temperatura getTemperatura() {
    return temperatura;
  }
}
