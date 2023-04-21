package org.quemepongo.Models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.quemepongo.Utils.CampoRequeridoException;

class PrendaTest {


  @Test
  // 1) Se puede crear una prenda indicando correctamente los campos requeridos
  void sePuedeCrearUnaPrendaIndicandoSusCamposSiSonValidos() {
    assertDoesNotThrow(() -> new Prenda(TipoPrenda.BOTAS, Material.CUERO, Color.VERDE, Color.AZUL));
  }

  @Test
  // 2) No debe permitirse crear una prenda si falta algun campo requerido
  void noSePuedeCrearUnaPrendaSiFaltaAlgunCampoRequerido() {
    CampoRequeridoException campoRequeridoException = assertThrows(CampoRequeridoException.class, () -> new Prenda(TipoPrenda.BOTAS, Material.CUERO, null));
    assertEquals("El campo color principal es requerido", campoRequeridoException.getMessage());
  }

  @Test
  // 3) Se puede crear una prenda aunque no se indique color secundario
  void sePuedeCrearUnaPrendaSinIndicarColorSecundario() {
    assertDoesNotThrow(() -> new Prenda(TipoPrenda.BOTAS, Material.CUERO, Color.VERDE));
  }

  @Test
  // 3) Se puede obtener la categoria de una prenda
  void sePuedeIdentificarLaCategoriaDeUnaPrenda() {
    Prenda prenda = new Prenda(TipoPrenda.BOTAS, Material.CUERO, Color.VERDE);
    assertEquals(Categoria.CALZADO, prenda.getCategoria());
  }
}