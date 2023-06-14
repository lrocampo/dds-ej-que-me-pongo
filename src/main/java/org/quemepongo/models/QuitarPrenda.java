package org.quemepongo.models;

public class QuitarPrenda extends PropuestaModificacion {

  public QuitarPrenda(Guardarropa guardarropa, Prenda prenda) {
    super(guardarropa, prenda);
  }

  @Override
  public void efecto() {
    guardarropa.quitarPrenda(prenda);
  }

  @Override
  public void restaurarGuardarropa() {
    guardarropa.cargarPrenda(prenda);
  }
}
