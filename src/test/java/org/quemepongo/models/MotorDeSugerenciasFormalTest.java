package org.quemepongo.models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MotorDeSugerenciasFormalTest {
    @Test
    void deberiaGenerarTodasLasSugerenciasPosiblesSiendoViejo() {
        List<Prenda> prendaList = List.of(unaParteSuperior(Formalidad.FORMAL),
                unaParteInferior(Formalidad.FORMAL),
                unCalzado(Formalidad.FORMAL),
                unaParteSuperior(Formalidad.NEUTRA),
                unaParteInferior(Formalidad.NEUTRA),
                unCalzado(Formalidad.NEUTRA));
        Usuario usuario = new Usuario(prendaList, 56, new MotorDeSugerenciasFormal());
        assertEquals(1, usuario.generarSugerencias().size());
    }

    @Test
    void deberiaGenerarTodasLasSugerenciasPosiblesSiendoNoTanViejo() {
        List<Prenda> prendaList = List.of(unaParteSuperior(Formalidad.FORMAL),
                unaParteInferior(Formalidad.FORMAL),
                unCalzado(Formalidad.FORMAL),
                unaParteSuperior(Formalidad.NEUTRA),
                unaParteInferior(Formalidad.NEUTRA),
                unCalzado(Formalidad.NEUTRA));
        Usuario usuario = new Usuario(prendaList, 54, new MotorDeSugerenciasFormal());
        assertEquals(8, usuario.generarSugerencias().size());
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