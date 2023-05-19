package org.quemepongo.utils;


import org.quemepongo.services.ServicioClima;

public class ProveedorDeClima {
  static ProveedorDeClima INSTANCE = new ProveedorDeClima();
  private ServicioClima servicioClima;

  static ProveedorDeClima getInstance(){
    return INSTANCE;
  }

  public ServicioClima getMotor(){
    return servicioClima;
  }

  public void setMotor(ServicioClima motor){
    this.servicioClima = motor;
  }
}
