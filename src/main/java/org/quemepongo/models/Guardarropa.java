package org.quemepongo.models;

import java.util.ArrayList;
import java.util.List;

public class Guardarropa {
  private List<Prenda> prendas;
  private String criterio;
  private MotorDeSugerencias motorDeSugerencias;

  public Guardarropa(List<Prenda> prendas, String criterio, MotorDeSugerencias motorDeSugerencias) {
    this.prendas = new ArrayList<>(prendas);
    this.criterio = criterio;
    this.motorDeSugerencias = motorDeSugerencias;
  }

  public String getCriterio() {
    return criterio;
  }

  public List<Sugerencia> generarSugerencias(Usuario usuario) {
    return motorDeSugerencias.generarSugerencias(usuario, prendas);
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
