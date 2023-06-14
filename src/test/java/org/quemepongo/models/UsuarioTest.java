package org.quemepongo.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.quemepongo.enums.Color;
import org.quemepongo.enums.Formalidad;
import org.quemepongo.enums.Material;
import org.quemepongo.enums.TipoPrenda;
import org.quemepongo.exceptions.DomainException;
import org.quemepongo.utils.PrendaBorrador;

class UsuarioTest {
  Prenda prenda;
  Guardarropa guardarropaEntreCasa;
  Usuario usuario1;
  Usuario usuario2;
  @BeforeEach
  void setUp() {
    prenda = unaPrenda();
    guardarropaEntreCasa = new Guardarropa(List.of(prenda), new MotorDeSugerenciasBasico());
    usuario1 = new Usuario(22);
    usuario1.agregarGuardarropa(guardarropaEntreCasa);
    usuario2 = new Usuario(22);
  }

  @Test
  void sePuedeProponerAgregarUnaPrendaAUnUsuario() {

    usuario1.agregarPrendaTentativa(prenda, guardarropaEntreCasa);

    assertEquals(1, usuario1.getPropuestasPendientes().size());
  }

  @Test
  void unUsuarioPuedeProponerQuitarUnaPrendaAOtroUsuario() {
    usuario1.removerPrendaTentativa(prenda, guardarropaEntreCasa);

    assertEquals(1, usuario1.getPropuestasPendientes().size());
  }

  @Test
  void unUsuarioPuedeAceptarPropuestas() {
    usuario1.agregarPrendaTentativa(prenda, guardarropaEntreCasa);

    usuario1.getPropuestasPendientes().get(0).aplicar();

    assertEquals(0, usuario1.getPropuestasPendientes().size());
    assertTrue(guardarropaEntreCasa.getPrendas().contains(prenda));
  }

  @Test
  void unUsuarioPuedeRechazarPropuestas() {
    Prenda otraPrenda = unaPrenda();
    usuario1.agregarPrendaTentativa(unaPrenda(), guardarropaEntreCasa);

    usuario1.getPropuestasPendientes().forEach(PropuestaModificacion::rechazar);

    assertEquals(0, usuario1.getPropuestasPendientes().size());
    assertFalse(guardarropaEntreCasa.getPrendas().contains(otraPrenda));
  }

  @Test
  void unUsuarioPuedeDeshacerAgregarUnaPrenda() {
    usuario1.agregarPrendaTentativa(prenda, guardarropaEntreCasa);
    usuario1.agregarPrendaTentativa(prenda, guardarropaEntreCasa);

    usuario1.getPropuestasPendientes().forEach(PropuestaModificacion::aplicar);
    usuario1.getPropuestasAceptadas().get(0).deshacer();

    assertEquals(2, guardarropaEntreCasa.getPrendas().size());
  }

  @Test
  void unUsuarioPuedeDeshacerQuitarUnaPrenda() {
    usuario1.agregarPrendaTentativa(prenda, guardarropaEntreCasa);
    usuario1.removerPrendaTentativa(prenda, guardarropaEntreCasa);

    usuario1.getPropuestasPendientes().forEach(PropuestaModificacion::aplicar);
    usuario1.getPropuestasAceptadas().get(1).deshacer();

    assertEquals(2, guardarropaEntreCasa.getPrendas().size());
  }

  @Test
  void unUsuarioNoPuedeDeshacerUnaPropuestaQueNoFueAceptada() {
    usuario1.agregarPrendaTentativa(prenda, guardarropaEntreCasa);
    PropuestaModificacion propuestaModificacion = usuario1.getPropuestasPendientes().get(0);
    assertThrows(DomainException.class, propuestaModificacion::deshacer);
  }

  private Prenda unaPrenda() {
    return new PrendaBorrador(TipoPrenda.REMERA_MANGAS_CORTAS)
        .conMaterial(Material.ALGODON)
        .conColorPrincipal(Color.VERDE)
        .conFormalidad(Formalidad.INFORMAL)
        .construirPrenda();
  }
}