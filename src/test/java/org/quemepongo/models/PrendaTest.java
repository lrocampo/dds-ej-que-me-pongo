package org.quemepongo.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.quemepongo.enums.*;
import org.quemepongo.utils.PrendaBorrador;

public class PrendaTest {

  @Test
  // 1) Se puede crear una prenda indicando correctamente los campos requeridos
  public void crearUnaPrendaIndicandoPartesValidas() {
    assertDoesNotThrow(() -> borradorCompleto().construirPrenda());
  }

  @Test
  // 2) No debe permitirse crear una prenda si falta algun campo requerido
  public void siFaltaAlgunCampoRequerido() {
    IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () -> borradorConTipo().construirPrenda());
    assertEquals("El campo color principal es requerido", excepcion.getMessage());
  }

  @Test
  // 3) Se puede crear una prenda aunque no se indique color secundario
  public void crearUnaPrendaSinIndicarColorSecundario() {
    assertDoesNotThrow(() -> borradorConTipo().conColorPrincipal(Color.VERDE).construirPrenda());
  }

  @Test
  // 4) Se puede obtener la categoria de una prenda
  public void identificarLaCategoriaDeUnaPrenda() {
    Prenda prenda = borradorCompleto().construirPrenda();
    assertEquals(Categoria.PARTE_SUPERIOR, prenda.getCategoria());
  }

  @Test
  // 5) Se puede cargar una prenda sin trama, tiene un valor por defecto
  public void crearUnaPrendaSinIndicarTrama() {
    Prenda prenda = borradorConTipo().conColorPrincipal(Color.VERDE)
                                     .conColorSecundario(Color.AMARILLO)
                                     .construirPrenda();
    assertEquals(Trama.LISA, prenda.getTrama());
  }

  private PrendaBorrador borradorConTipo() {
    return new PrendaBorrador(TipoPrenda.REMERA_MANGAS_CORTAS)
            .conMaterial(Material.ALGODON)
            .conFormalidad(Formalidad.INFORMAL);
  }

  private PrendaBorrador borradorCompleto() {
    return borradorConTipo().conColorPrincipal(Color.VERDE)
                            .conColorSecundario(Color.AMARILLO)
                            .conFormalidad(Formalidad.INFORMAL)
                            .conTrama(Trama.CUADROS);
  }

}