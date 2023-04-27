package org.quemepongo.Models;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class QueMePongoTest {
  private static Prenda unaPrenda;
  private static QueMePongo queMePongo;

  @BeforeAll
  public static void setUp() {
    queMePongo = new QueMePongo();
    unaPrenda = new Prenda(TipoPrenda.REMERA_MANGAS_CORTAS, Material.ALGODON, Color.VERDE, null, null);
  }

  @Test
  // se puede cargar prendas validas
  public void cargarUnaPrenda() {
    assertDoesNotThrow(() -> queMePongo.cargarPrenda(unaPrenda));
  }

}
