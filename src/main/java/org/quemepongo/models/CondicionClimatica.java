package org.quemepongo.models;

public class CondicionClimatica {
  private Humedad humedad;
  private Temperatura temperatura;

  public CondicionClimatica(Humedad probabilidadPrecipitacion, Temperatura temperatura) {
    this.humedad = probabilidadPrecipitacion;
    this.temperatura = temperatura;
  }

  public Humedad getHumedad() {
    return humedad;
  }

  public Temperatura getTemperatura() {
    return temperatura;
  }
}
