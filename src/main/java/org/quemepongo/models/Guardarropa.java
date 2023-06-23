package org.quemepongo.models;

import java.util.ArrayList;
import java.util.List;
import org.quemepongo.services.ServicioClima;

public class Guardarropa {
  private List<Prenda> prendas;
  private String criterio;
  private MotorDeSugerencias motorDeSugerencias;

  private ServicioClima servicioClima;

  public Guardarropa(List<Prenda> prendas, String criterio, MotorDeSugerencias motorDeSugerencias, ServicioClima servicioClima) {
    this.prendas = new ArrayList<>(prendas);
    this.motorDeSugerencias = motorDeSugerencias;
    this.servicioClima = servicioClima;
  }

  public String getCriterio() {
    return criterio;
  }

  public void setCriterio(String criterio) {
    this.criterio = criterio;
  }

  public List<Sugerencia> generarSugerencias(Usuario usuario) {
    return motorDeSugerencias.generarSugerencias(usuario, prendas);
  }

  public List<Sugerencia> generarSugerenciasDiaria(Usuario usuario) {
    return this.generarSugerencias(usuario).stream().filter(sugerencia -> sugerencia.esAptaTemperatura(servicioClima.getTemperaturaActual())).toList();
  }

  public void cargarPrenda(Prenda prenda) {
    prendas.add(prenda);
  }

  public void quitarPrenda(Prenda prenda) {
    prendas.remove(prenda);
  }


  public Sugerencia generarSugerencia(Usuario usuario) {
    return motorDeSugerencias.generarSugerencia(usuario, prendas);
  }

  public List<Prenda> getPrendas() {
    return prendas;
  }
}
