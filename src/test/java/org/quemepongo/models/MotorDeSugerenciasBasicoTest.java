package org.quemepongo.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.quemepongo.enums.Color;
import org.quemepongo.enums.Formalidad;
import org.quemepongo.enums.Material;
import org.quemepongo.enums.TipoPrenda;
import org.quemepongo.exceptions.DomainException;

import java.util.List;
import org.quemepongo.services.ServicioClima;
import org.quemepongo.utils.PrendaBorrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MotorDeSugerenciasBasicoTest {

    ServicioClima servicioClima;

    @BeforeEach
    public void setUp() {
        servicioClima = Mockito.mock(ServicioClima.class);

        when(servicioClima.getTemperaturaActual()).thenReturn(new Celsius(20));
    }

    @Test
    void deberiaGenerarTodasLasSugerenciasPosibles() {
        List<Prenda> prendaList = List.of(unaParteSuperior(Formalidad.INFORMAL),
                unaParteInferior(Formalidad.INFORMAL),
                unCalzado(Formalidad.INFORMAL),
                unaParteSuperior(Formalidad.NEUTRA),
                unaParteInferior(Formalidad.NEUTRA),
                unCalzado(Formalidad.NEUTRA));
        Guardarropa guardarropas = new Guardarropa(prendaList, "c", new MotorDeSugerenciasBasico(), servicioClima);
        Usuario usuario = new Usuario(List.of(guardarropas), 22);
        assertEquals(8, usuario.generarSugerencias("c").size());
    }

    @Test
    void noDeberiaGenerarTodasLasSugerencias() {
        List<Prenda> prendaList = List.of(unaParteSuperior(Formalidad.INFORMAL),
                unaParteInferior(Formalidad.INFORMAL));
        Guardarropa guardarropas = new Guardarropa(prendaList, "c", new MotorDeSugerenciasBasico(), servicioClima);
        Usuario usuario = new Usuario(List.of(guardarropas), 22);
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
        Guardarropa guardarropas = new Guardarropa(prendaList, "c", new MotorDeSugerenciasBasico(), servicioClima);
        Usuario usuario = new Usuario(List.of(guardarropas), 22);
        assertDoesNotThrow(usuario::generarSugerencia);
    }

    @Test
    void noDeberiaGenerarUnaSugerencia() {
        List<Prenda> prendaList = List.of(unaParteSuperior(Formalidad.INFORMAL),
                unaParteInferior(Formalidad.INFORMAL));
        Guardarropa guardarropas = new Guardarropa(prendaList, "c", new MotorDeSugerenciasBasico(), servicioClima);
        Usuario usuario = new Usuario(List.of(guardarropas), 22);
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