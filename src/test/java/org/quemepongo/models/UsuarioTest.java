package org.quemepongo.models;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.quemepongo.enums.Color;
import org.quemepongo.enums.Formalidad;
import org.quemepongo.enums.Material;
import org.quemepongo.enums.TipoPrenda;
import org.quemepongo.exceptions.DomainException;
import org.quemepongo.services.ServicioClima;
import org.quemepongo.utils.PrendaBorrador;

class UsuarioTest {
  Prenda prenda;
  Guardarropa guardarropaEntreCasa;
  Usuario usuario1;
  Usuario usuario2;
  ServicioClima servicioClima;

  @BeforeEach
  void setUp() {
    prenda = unaPrenda();
    servicioClima = Mockito.mock(ServicioClima.class);
    when(servicioClima.getTemperaturaActual()).thenReturn(new Celsius(20));
    guardarropaEntreCasa = new Guardarropa(List.of(prenda), "Ropa de Entrecasa Compartida", new MotorDeSugerenciasBasico(), servicioClima);
    usuario1 = new Usuario(List.of(guardarropaEntreCasa), 22);
    usuario2 = new Usuario(List.of(), 22);
  }

  @Test
  void unUsuarioPuedeProponerAgregarUnaPrendaAOtroUsuario() {
    PropuestaModificacion propuesta = new AgregarPrenda(guardarropaEntreCasa, unaPrenda());

    usuario2.proponerModificacion(propuesta, usuario1);

    assertEquals(propuesta, usuario1.getPropuestasPendientes().get(0));
  }

  @Test
  void unUsuarioPuedeProponerQuitarUnaPrendaAOtroUsuario() {
    PropuestaModificacion propuesta = new QuitarPrenda(guardarropaEntreCasa, prenda);

    usuario2.proponerModificacion(propuesta, usuario1);

    assertEquals(propuesta, usuario1.getPropuestasPendientes().get(0));
  }

  @Test
  void unUsuarioPuedeAceptarPropuestas() {
    PropuestaModificacion propuesta = new AgregarPrenda(guardarropaEntreCasa, prenda);

    usuario2.proponerModificacion(propuesta, usuario1);

    usuario1.getPropuestasPendientes().forEach(usuario1::aceptarPropuesta);

    assertEquals(0, usuario1.getPropuestasPendientes().size());
    assertTrue(guardarropaEntreCasa.getPrendas().contains(prenda));
  }

  @Test
  void unUsuarioPuedeRechazarPropuestas() {
    Prenda otraPrenda = unaPrenda();
    PropuestaModificacion propuesta = new AgregarPrenda(guardarropaEntreCasa, otraPrenda);

    usuario2.proponerModificacion(propuesta, usuario1);

    usuario1.getPropuestasPendientes().forEach(usuario1::rechazarPropuesta);

    assertEquals(0, usuario1.getPropuestasPendientes().size());
    assertFalse(guardarropaEntreCasa.getPrendas().contains(otraPrenda));
  }

  @Test
  void unUsuarioPuedeDeshacerAgregarUnaPrenda() {
    PropuestaModificacion propuesta1 = new AgregarPrenda(guardarropaEntreCasa, prenda);
    PropuestaModificacion propuesta2 = new AgregarPrenda(guardarropaEntreCasa, prenda);


    usuario2.proponerModificacion(propuesta1, usuario1);
    usuario2.proponerModificacion(propuesta2, usuario1);

    usuario1.getPropuestasPendientes().forEach(usuario1::aceptarPropuesta);

    usuario1.deshacerPropuesta(propuesta2);

    assertEquals(2, guardarropaEntreCasa.getPrendas().size());
  }

  @Test
  void unUsuarioPuedeDeshacerQuitarUnaPrenda() {
    PropuestaModificacion propuesta1 = new AgregarPrenda(guardarropaEntreCasa, prenda);
    PropuestaModificacion propuesta2 = new QuitarPrenda(guardarropaEntreCasa, prenda);


    usuario2.proponerModificacion(propuesta1, usuario1);
    usuario2.proponerModificacion(propuesta2, usuario1);

    usuario1.getPropuestasPendientes().forEach(usuario1::aceptarPropuesta);

    usuario1.deshacerPropuesta(propuesta2);

    assertEquals(2, guardarropaEntreCasa.getPrendas().size());
  }

  @Test
  void unUsuarioNoPuedeDeshacerUnaPropuestaQueNoFueAceptada() {
    PropuestaModificacion propuesta1 = new AgregarPrenda(guardarropaEntreCasa, prenda);
    usuario2.proponerModificacion(propuesta1, usuario1);

    assertThrows(DomainException.class, () -> {usuario1.deshacerPropuesta(propuesta1);});
  }

  private Prenda unaPrenda() {
    return new PrendaBorrador(TipoPrenda.REMERA_MANGAS_CORTAS)
        .conMaterial(Material.ALGODON)
        .conColorPrincipal(Color.VERDE)
        .conFormalidad(Formalidad.INFORMAL)
        .construirPrenda();
  }
}