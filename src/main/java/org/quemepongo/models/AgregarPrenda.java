package org.quemepongo.models;

public class AgregarPrenda extends PropuestaModificacion {
  public AgregarPrenda(Guardarropa guardarropa, Prenda prenda) {
    super(guardarropa, prenda);
  }

  @Override
  public void efecto() {
    guardarropa.cargarPrenda(prenda);
  }

  @Override
  public void restaurarGuardarropa() {
    guardarropa.quitarPrenda(prenda);
  }
}
