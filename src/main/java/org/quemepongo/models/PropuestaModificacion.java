package org.quemepongo.models;

import org.quemepongo.exceptions.DomainException;

public abstract class PropuestaModificacion {
  protected Guardarropa guardarropa;
  protected Prenda prenda;
  protected boolean aceptada = false;

  public PropuestaModificacion(Guardarropa guardarropa, Prenda prenda) {
    this.guardarropa = guardarropa;
    this.prenda = prenda;
  }

  public abstract void aplicar();
  public abstract void restaurarGuardarropa();
  public void deshacer() {
    if(aceptada) {
      restaurarGuardarropa();
    }
    else throw new DomainException("No se puede deshacer una propuesta que no fue aceptada");
  }
  public boolean isAceptada() {
    return aceptada;
  }
}
