package org.quemepongo.models;

import java.util.List;
import org.quemepongo.services.ServicioClima;

public class MotorDeSugerenciasClimatico extends MotorDeSugerencias {
  private ServicioClima servicioClima;

  public MotorDeSugerenciasClimatico(ServicioClima servicioClima) {
    this.servicioClima = servicioClima;
  }

  @Override
  public List<Prenda> getPrendasValidas(Usuario usuario, List<Prenda> prendas) {
    Temperatura temperatura = servicioClima.getTemperaturaActual();
    return prendas.stream().filter(prenda -> prenda.esApta(temperatura)).toList();
  }
}
