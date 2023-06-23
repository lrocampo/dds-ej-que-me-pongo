package org.quemepongo.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.quemepongo.enums.Color;
import org.quemepongo.enums.Formalidad;
import org.quemepongo.enums.Material;
import org.quemepongo.enums.TipoPrenda;
import org.quemepongo.services.ServicioClima;
import org.quemepongo.utils.PrendaBorrador;

public class GuardarropasTest {

  MotorDeSugerencias motor;
  ServicioClima servicioClima;

  @BeforeEach
  public void setUp() {
    servicioClima = Mockito.mock(ServicioClima.class);
    motor = Mockito.mock(MotorDeSugerenciasBasico.class);

    when(servicioClima.getTemperaturaActual()).thenReturn(new Celsius(20));
  }

  @Test
  void sePuedeManejarVariosGuardarropasPorCriterio() {
    List<Prenda> prendaList = List.of(unaParteSuperior(Formalidad.INFORMAL),
        unaParteInferior(Formalidad.INFORMAL),
        unCalzado(Formalidad.INFORMAL));
    Guardarropa guardarropaEntreCasa = new Guardarropa(prendaList,  "Ropa de Entrecasa", new MotorDeSugerenciasBasico(), servicioClima);
    Usuario usuario = new Usuario(List.of(guardarropaEntreCasa), 22);

    List<Sugerencia> sugerencias = List.of(new Sugerencia(prendaList.get(0), prendaList.get(1), prendaList.get(2)));

    when(motor.generarSugerencias(usuario, prendaList)).thenReturn(sugerencias);

    assertEquals(1, usuario.generarSugerencias().size());
    assertEquals(sugerencias.get(0), usuario.generarSugerencias().get(0));
  }

  @Test
  void sePuedeCompartirGuardarropasConOtros() {
    Guardarropa guardarropaEntreCasa = new Guardarropa(List.of(), "Ropa de Entrecasa Compartida", new MotorDeSugerenciasBasico(), servicioClima);
    Usuario usuario1 = new Usuario(List.of(guardarropaEntreCasa), 22);
    Usuario usuario2 = new Usuario(List.of(), 22);
    usuario1.agregarGuardarropa(guardarropaEntreCasa);
    usuario2.agregarGuardarropa(guardarropaEntreCasa);

    assertEquals(guardarropaEntreCasa, usuario2.getGuardarropas().get(0));
  }

  private Prenda unaParteSuperior(Formalidad formalidad) {
    return new PrendaBorrador(TipoPrenda.REMERA_MANGAS_CORTAS)
        .conMaterial(Material.ALGODON)
        .conColorPrincipal(Color.VERDE)
        .conFormalidad(formalidad)
        .construirPrenda();
  }

  private Prenda unaParteInferior(Formalidad formalidad) {
    return new PrendaBorrador(TipoPrenda.PANTALON)
        .conMaterial(Material.ALGODON)
        .conColorPrincipal(Color.VERDE)
        .conFormalidad(formalidad)
        .construirPrenda();
  }

  private Prenda unCalzado(Formalidad formalidad) {
    return new PrendaBorrador(TipoPrenda.BOTAS)
        .conMaterial(Material.CUERO)
        .conColorPrincipal(Color.VERDE)
        .conFormalidad(formalidad)
        .construirPrenda();
  }

}
