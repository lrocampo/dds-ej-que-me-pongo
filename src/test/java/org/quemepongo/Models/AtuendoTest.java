package org.quemepongo.Models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AtuendoTest {
  private static Prenda unaParteSuperior;
  private static Prenda unaParteInferior;
  private static Prenda unCalzado;
  private static Prenda unAccesorio;

  @BeforeAll
  public static void setUp() {
    unaParteSuperior = new Prenda(TipoPrenda.REMERA_MANGAS_CORTAS, Material.ALGODON, Color.VERDE);
    unaParteInferior = new Prenda(TipoPrenda.PANTALON, Material.JEAN, Color.AZUL);
    unCalzado = new Prenda(TipoPrenda.BOTAS, Material.CUERO, Color.VERDE);
    unAccesorio = new Prenda(TipoPrenda.PANUELO, Material.CUERO, Color.VERDE);
  }

  @Test
  // 1) se puede crear un atuendo a partir de prendas con las categorias correctas
  public void sePuedeCrearUnAtuendoIndicandoPrendas() {
    assertDoesNotThrow(() -> new Atuendo(unaParteSuperior, unaParteInferior, unCalzado));
  }

  @Test
  // 2) No se puede crear un atuendo si falta alguna prenda obligatoria
  public void noSePuedeCrearUnAtuendoSiFaltaAlgunCampoRequerido() {
    IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () -> new Atuendo(null, unaParteInferior, unCalzado));
    assertEquals("La parte superior es requerida", excepcion.getMessage());
  }

  @Test
  // 3) No se puede crear un atuendo si las prendas son incorrectas
  public void noSePuedeCrearUnAtuendoIndicandoPrendasIncorrectas() {
    IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () -> new Atuendo(unCalzado, unaParteInferior, unCalzado));
    assertEquals("La prenda no es parte superior", excepcion.getMessage());
  }

  @Test
  // 4) Se pueden agregar accesorios
  public void sePuedeAgregarUnAccesorio() {
    Atuendo unAtuendo = new Atuendo(unaParteSuperior, unaParteInferior, unCalzado);
    assertDoesNotThrow(() -> unAtuendo.agregarAccesorio(unAccesorio));
  }

  @Test
  // 5) No se pueden agregar accesorios
  public void noSePuedeAgregarUnAccesorioQueNoEsUnAccesorio() {
    Atuendo unAtuendo = new Atuendo(unaParteSuperior, unaParteInferior, unCalzado);
    IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () -> unAtuendo.agregarAccesorio(unaParteInferior));
    assertEquals("La prenda no es un accesorio", excepcion.getMessage());
  }
}
