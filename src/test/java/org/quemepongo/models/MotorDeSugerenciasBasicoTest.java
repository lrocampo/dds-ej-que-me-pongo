package org.quemepongo.models;

import org.junit.jupiter.api.Test;
import org.quemepongo.utils.DomainException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MotorDeSugerenciasBasicoTest {

    @Test
    void deberiaGenerarTodasLasSugerenciasPosibles() {
        List<Prenda> prendaList = List.of(unaParteSuperior(Formalidad.INFORMAL),
                unaParteInferior(Formalidad.INFORMAL),
                unCalzado(Formalidad.INFORMAL),
                unaParteSuperior(Formalidad.NEUTRA),
                unaParteInferior(Formalidad.NEUTRA),
                unCalzado(Formalidad.NEUTRA));
        Usuario usuario = new Usuario(prendaList, 22, new MotorDeSugerenciasBasico());
        assertEquals(8, usuario.generarSugerencias().size());
    }

    @Test
    void noDeberiaGenerarTodasLasSugerencias() {
        List<Prenda> prendaList = List.of(unaParteSuperior(Formalidad.INFORMAL),
                unaParteInferior(Formalidad.INFORMAL));
        Usuario usuario = new Usuario(prendaList, 22, new MotorDeSugerenciasBasico());
        assertEquals(0, usuario.generarSugerencias().size());
    }

    @Test
    void deberiaGenerarUnaSugerencia() {
        List<Prenda> prendaList = List.of(unaParteSuperior(Formalidad.INFORMAL),
                unaParteInferior(Formalidad.INFORMAL),
                unCalzado(Formalidad.INFORMAL),
                unaParteSuperior(Formalidad.NEUTRA),
                unaParteInferior(Formalidad.NEUTRA),
                unCalzado(Formalidad.NEUTRA));
        Usuario usuario = new Usuario(prendaList, 22, new MotorDeSugerenciasBasico());
        assertDoesNotThrow(usuario::generarSugerencia);
    }

    @Test
    void noDeberiaGenerarUnaSugerencia() {
        List<Prenda> prendaList = List.of(unaParteSuperior(Formalidad.INFORMAL),
                unaParteInferior(Formalidad.INFORMAL));
        Usuario usuario = new Usuario(prendaList, 22, new MotorDeSugerenciasBasico());
        assertThrows(DomainException.class, usuario::generarSugerencia);
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