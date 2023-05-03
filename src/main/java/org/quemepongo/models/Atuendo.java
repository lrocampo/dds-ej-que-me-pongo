package org.quemepongo.models;

import java.util.HashSet;
import java.util.Set;

public class Atuendo {
  private Prenda parteSuperior;
  private Prenda parteInferior;
  private Prenda calzado;
  private Set<Prenda> accesorios = new HashSet<>();


  public Atuendo(Prenda parteSuperior, Prenda parteInferior, Prenda calzado) {
    validarRequeridos(parteSuperior, parteInferior, calzado);
    validarCategorias(parteSuperior, parteInferior, calzado);
    this.parteSuperior = parteSuperior;
    this.parteInferior = parteInferior;
    this.calzado = calzado;
  }

  public void agregarAccesorio(Prenda accesorio) {
    if (!accesorio.esAccesorio()) {
      throw new IllegalArgumentException("La prenda no es un accesorio");
    }
    this.accesorios.add(accesorio);
  }

  private void validarRequeridos(Prenda parteSuperior, Prenda parteInferior, Prenda calzado) {
    if (parteSuperior == null) {
      throw new IllegalArgumentException("La parte superior es requerida");
    }
    if (parteInferior == null) {
      throw new IllegalArgumentException("La parte inferior es requerida");
    }
    if (calzado == null) {
      throw new IllegalArgumentException("El calzado es requerido");
    }
  }

  private void validarCategorias(Prenda parteSuperior, Prenda parteInferior, Prenda calzado) {
    if (!parteSuperior.esSuperior()) {
      throw new IllegalArgumentException("La prenda no es parte superior");
    }
    if (!parteInferior.esInferior()) {
      throw new IllegalArgumentException("La prenda no es parte inferior");
    }
    if (!calzado.esCalzado()) {
      throw new IllegalArgumentException("La prenda no es calzado");
    }
  }
}
