package org.quemepongo.models;

import org.junit.jupiter.api.Test;

import java.util.List;
import org.quemepongo.enums.Color;
import org.quemepongo.enums.Formalidad;
import org.quemepongo.enums.Material;
import org.quemepongo.enums.TipoPrenda;
import org.quemepongo.utils.PrendaBorrador;

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
        Guardarropa guardarropas = new Guardarropa(prendaList, "c", new MotorDeSugerenciasFormal());
        Usuario usuario = new Usuario(56);

        usuario.agregarGuardarropa(guardarropas);
        assertEquals(1, usuario.generarSugerencias("c").size());
    }

    @Test
    void deberiaGenerarTodasLasSugerenciasPosiblesSiendoNoTanViejo() {
        List<Prenda> prendaList = List.of(unaParteSuperior(Formalidad.FORMAL),
                unaParteInferior(Formalidad.FORMAL),
                unCalzado(Formalidad.FORMAL),
                unaParteSuperior(Formalidad.NEUTRA),
                unaParteInferior(Formalidad.NEUTRA),
                unCalzado(Formalidad.NEUTRA));
        Guardarropa guardarropas = new Guardarropa(prendaList, "c", new MotorDeSugerenciasFormal());
        Usuario usuario = new Usuario(22);

        usuario.agregarGuardarropa(guardarropas);
        assertEquals(8, usuario.generarSugerencias("c").size());
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