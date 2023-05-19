package org.quemepongo.models;

import java.util.List;
import org.quemepongo.services.ServicioClima;

public class MotorDeSugerenciasClimatico extends MotorDeSugerencias {
  private ServicioClima servicioClima;

  public MotorDeSugerenciasClimatico(ServicioClima servicioClima) {
    this.servicioClima = servicioClima;
  }

  @Override
  public List<Prenda> getPrendasValidas(Usuario usuario) {
    Temperatura temperatura = servicioClima.getTemperaturaActual();
    return usuario.getPrendas().stream().filter(prenda -> prenda.esApta(temperatura)).toList();
  }
}