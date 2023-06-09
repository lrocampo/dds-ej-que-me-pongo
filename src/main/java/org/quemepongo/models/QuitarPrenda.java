package org.quemepongo.models;

public class QuitarPrenda extends PropuestaModificacion {

  public QuitarPrenda(Guardarropa guardarropa, Prenda prenda) {
    super(guardarropa, prenda);
  }

  @Override
  public void aplicar() {
    guardarropa.quitarPrenda(prenda);
    aceptada = true;
  }

  @Override
  public void restaurarGuardarropa() {
    guardarropa.cargarPrenda(prenda);
  }
}
