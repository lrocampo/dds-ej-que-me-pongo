package org.quemepongo.Models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PrendaTest {

  @Test
  // 1) Se puede crear una prenda indicando correctamente los campos requeridos
  public void crearUnaPrendaIndicandoPartesValidas() {
    assertDoesNotThrow(() -> new Prenda(TipoPrenda.BOTAS, Material.CUERO, Color.VERDE, Color.AZUL, null));
  }

  @Test
  // 2) No debe permitirse crear una prenda si falta algun campo requerido
  public void siFaltaAlgunCampoRequerido() {
    IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () -> new Prenda(TipoPrenda.BOTAS, Material.CUERO, null, null, null));
    assertEquals("El campo color principal es requerido", excepcion.getMessage());
  }

  @Test
  // 3) Se puede crear una prenda aunque no se indique color secundario
  public void crearUnaPrendaSinIndicarColorSecundario() {
    assertDoesNotThrow(() -> new Prenda(TipoPrenda.BOTAS, Material.CUERO, Color.VERDE, null, null));
  }

  @Test
  // 4) Se puede obtener la categoria de una prenda
  public void identificarLaCategoriaDeUnaPrenda() {
    Prenda prenda = new Prenda(TipoPrenda.BOTAS, Material.CUERO, Color.VERDE, null, null);
    assertEquals(Categoria.CALZADO, prenda.getCategoria());
  }

  @Test
  // 5) Se puede cargar una prenda sin trama, tiene un valor por defecto
  public void crearUnaPrendaSinIndicarTrama() {
    Prenda prenda = new Prenda(TipoPrenda.BOTAS, Material.CUERO, Color.VERDE, null, null);
    assertEquals(Trama.LISA, prenda.getTrama());
  }
}