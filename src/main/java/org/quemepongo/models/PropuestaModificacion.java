package org.quemepongo.models;

import static org.quemepongo.enums.EstadoPropuesta.ACEPTADA;
import static org.quemepongo.enums.EstadoPropuesta.PENDIENTE;
import static org.quemepongo.enums.EstadoPropuesta.RECHAZADA;

import org.quemepongo.enums.EstadoPropuesta;
import org.quemepongo.exceptions.DomainException;

public abstract class PropuestaModificacion {
  protected Guardarropa guardarropa;
  protected Prenda prenda;
  protected EstadoPropuesta estado = PENDIENTE;

  public PropuestaModificacion(Guardarropa guardarropa, Prenda prenda) {
    this.guardarropa = guardarropa;
    this.prenda = prenda;
  }

  public void aplicar() {
    estado = ACEPTADA;
    efecto();
  }

  public void rechazar() {
    estado = RECHAZADA;
  }

  public abstract void efecto();

  public abstract void restaurarGuardarropa();

  public void deshacer() {
    if (isAceptada()) {
      restaurarGuardarropa();
    } else throw new DomainException("No se puede deshacer una propuesta que no fue aceptada");
  }

  public boolean isAceptada() {
    return estado.equals(ACEPTADA);
  }

  public boolean isPendiente() {
    return estado.equals(PENDIENTE);
  }
}
