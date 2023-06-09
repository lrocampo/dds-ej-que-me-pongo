package org.quemepongo.models;

public class AgregarPrenda extends PropuestaModificacion {
  public AgregarPrenda(Guardarropa guardarropa, Prenda prenda) {
    super(guardarropa, prenda);
  }

  @Override
  public void aplicar() {
    guardarropa.cargarPrenda(prenda);
    aceptada = true;
  }

  @Override
  public void restaurarGuardarropa() {
    guardarropa.quitarPrenda(prenda);
  }
}
