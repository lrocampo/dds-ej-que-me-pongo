package org.quemepongo.models;

import org.quemepongo.models.Temperatura;

public class Clima {
  private Temperatura temperatura;
  private Humedad humedad;

  public Clima(Temperatura temperatura, Humedad humedad){
    this.temperatura = temperatura;
    this.humedad = humedad;
  }

  public Temperatura getTemperatura(){ return temperatura; }
  public Humedad getHumedad(){ return humedad; }
}
