package org.quemepongo.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AtuendoTest {

  @Test
  // 1) se puede crear un atuendo a partir de prendas
  public void crearUnAtuendoIndicandoPrendas() {
    assertDoesNotThrow(() -> new Atuendo(unaParteSuperior(), unaParteInferior(), unCalzado()));
  }

  @Test
  // 2) No se puede crear un atuendo si falta alguna prenda obligatoria
  public void siFaltaAlgunCampoRequerido() {
    IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () -> new Atuendo(null, unaParteInferior(), unCalzado()));
    assertEquals("La parte superior es requerida", excepcion.getMessage());
  }

  @Test
  // 3) No se puede crear un atuendo si las prendas son incorrectas
  public void indicandoPrendasIncorrectas() {
    IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () -> new Atuendo(unCalzado(), unaParteSuperior(), unCalzado()));
    assertEquals("La prenda no es parte superior", excepcion.getMessage());
  }

  @Test
  // 4) Se pueden agregar accesorios
  public void agregarUnAccesorio() {
    Atuendo unAtuendo = new Atuendo(unaParteSuperior(), unaParteInferior(), unCalzado());
    assertDoesNotThrow(() -> unAtuendo.agregarAccesorio(unAccesorio()));
  }

  @Test
  // 5) No se pueden agregar accesorios
  public void alAgregarUnAccesorioQueNoEsUnAccesorio() {
    Atuendo unAtuendo = new Atuendo(unaParteSuperior(), unaParteInferior(), unCalzado());
    IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () -> unAtuendo.agregarAccesorio(unaParteInferior()));
    assertEquals("La prenda no es un accesorio", excepcion.getMessage());
  }

  private Prenda unaParteSuperior() {
    return new PrendaBorrador(TipoPrenda.REMERA_MANGAS_CORTAS)
        .conMaterial(Material.ALGODON)
        .conColorPrincipal(Color.VERDE)
        .conFormalidad(Formalidad.INFORMAL)
        .construirPrenda();
  }
  private Prenda unaParteInferior() {
    return new PrendaBorrador(TipoPrenda.PANTALON)
        .conMaterial(Material.ALGODON)
        .conColorPrincipal(Color.VERDE)
        .conFormalidad(Formalidad.FORMAL)
        .construirPrenda();
  }
  private Prenda unCalzado() {
    return new PrendaBorrador(TipoPrenda.BOTAS)
        .conMaterial(Material.CUERO)
        .conColorPrincipal(Color.VERDE)
        .conFormalidad(Formalidad.NEUTRA)
        .construirPrenda();
  }
  private Prenda unAccesorio() {
    return new PrendaBorrador(TipoPrenda.PANUELO)
        .conMaterial(Material.ALGODON)
        .conColorPrincipal(Color.VERDE)
        .conFormalidad(Formalidad.NEUTRA)
        .construirPrenda();
  }
}
