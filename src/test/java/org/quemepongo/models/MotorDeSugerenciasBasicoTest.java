package org.quemepongo.models;

import org.junit.jupiter.api.Test;
import org.quemepongo.enums.Color;
import org.quemepongo.enums.Formalidad;
import org.quemepongo.enums.Material;
import org.quemepongo.enums.TipoPrenda;
import org.quemepongo.exceptions.DomainException;

import java.util.List;
import org.quemepongo.utils.PrendaBorrador;

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
        Guardarropa guardarropas = new Guardarropa(prendaList, "c", new MotorDeSugerenciasBasico());
        Usuario usuario = new Usuario(56);

        usuario.agregarGuardarropa(guardarropas);
        assertEquals(8, usuario.generarSugerencias("c").size());
    }

    @Test
    void noDeberiaGenerarTodasLasSugerencias() {
        List<Prenda> prendaList = List.of(unaParteSuperior(Formalidad.INFORMAL),
                unaParteInferior(Formalidad.INFORMAL));
        Guardarropa guardarropas = new Guardarropa(prendaList, "c", new MotorDeSugerenciasBasico());
        Usuario usuario = new Usuario(56);

        usuario.agregarGuardarropa(guardarropas);
        assertEquals(0, usuario.generarSugerencias("c").size());
    }

    @Test
    void deberiaGenerarUnaSugerencia() {
        List<Prenda> prendaList = List.of(unaParteSuperior(Formalidad.INFORMAL),
                unaParteInferior(Formalidad.INFORMAL),
                unCalzado(Formalidad.INFORMAL),
                unaParteSuperior(Formalidad.NEUTRA),
                unaParteInferior(Formalidad.NEUTRA),
                unCalzado(Formalidad.NEUTRA));
        Guardarropa guardarropas = new Guardarropa(prendaList, "c", new MotorDeSugerenciasBasico());
        Usuario usuario = new Usuario(56);

        usuario.agregarGuardarropa(guardarropas);
        assertDoesNotThrow(usuario::generarSugerencia);
    }

    @Test
    void noDeberiaGenerarUnaSugerencia() {
        List<Prenda> prendaList = List.of(unaParteSuperior(Formalidad.INFORMAL),
                unaParteInferior(Formalidad.INFORMAL));
        Guardarropa guardarropas = new Guardarropa(prendaList, "c", new MotorDeSugerenciasBasico());
        Usuario usuario = new Usuario(56);

        usuario.agregarGuardarropa(guardarropas);
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