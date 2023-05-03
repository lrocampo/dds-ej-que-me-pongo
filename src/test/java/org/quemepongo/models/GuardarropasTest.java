package org.quemepongo.models;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GuardarropasTest {
  private static Guardarropas guardarropas;

  @BeforeEach
  public void setUp() {
    guardarropas = new Guardarropas();
  }

  @Test
  // se puede cargar prendas validas
  public void cargarUnaPrenda() {
    assertDoesNotThrow(() -> guardarropas.cargarPrenda(unaRemera()));
  }

  private Prenda unaRemera() {
    return new PrendaBorrador(TipoPrenda.REMERA_MANGAS_CORTAS).conMaterial(Material.ALGODON)
                                                              .conColorPrincipal(Color.VERDE)
                                                              .construirPrenda();
  }

}
