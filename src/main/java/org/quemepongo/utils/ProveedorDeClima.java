package org.quemepongo.utils;


import org.quemepongo.services.ServicioClima;

public class ProveedorDeClima {
  static ProveedorDeClima INSTANCE = new ProveedorDeClima();
  private ServicioClima servicioClima;

  static ProveedorDeClima getInstance(){
    return INSTANCE;
  }

  public ServicioClima getServicioClima(){
    return servicioClima;
  }

  public void setServicioClima(ServicioClima servicioClima){
    this.servicioClima = servicioClima;
  }
}
