package org.quemepongo.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import org.mockito.Mockito;
import org.quemepongo.enums.Color;
import org.quemepongo.enums.Formalidad;
import org.quemepongo.enums.Material;
import org.quemepongo.enums.TipoPrenda;
import org.quemepongo.services.ServicioClima;
import org.quemepongo.utils.PrendaBorrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MotorDeSugerenciasFormalTest {

    ServicioClima servicioClima;

    @BeforeEach
    public void setUp() {
        servicioClima = Mockito.mock(ServicioClima.class);

        when(servicioClima.getTemperaturaActual()).thenReturn(new Celsius(20));
    }
    @Test
    void deberiaGenerarTodasLasSugerenciasPosiblesSiendoViejo() {
        List<Prenda> prendaList = List.of(unaParteSuperior(Formalidad.FORMAL),
                unaParteInferior(Formalidad.FORMAL),
                unCalzado(Formalidad.FORMAL),
                unaParteSuperior(Formalidad.NEUTRA),
                unaParteInferior(Formalidad.NEUTRA),
                unCalzado(Formalidad.NEUTRA));
        Guardarropa guardarropas = new Guardarropa(prendaList, "c", new MotorDeSugerenciasFormal(), servicioClima);
        Usuario usuario = new Usuario(List.of(guardarropas), 56);
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
        Guardarropa guardarropas = new Guardarropa(prendaList, "c", new MotorDeSugerenciasFormal(), servicioClima);
        Usuario usuario = new Usuario(List.of(guardarropas), 54);
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