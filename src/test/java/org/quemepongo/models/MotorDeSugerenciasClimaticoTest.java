package org.quemepongo.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.quemepongo.enums.*;
import org.quemepongo.services.ServicioAccuWeather;
import org.quemepongo.utils.PrendaBorrador;

class MotorDeSugerenciasClimaticoTest {
  MotorDeSugerenciasClimatico motorDeSugerenciasClimatico;
  ServicioAccuWeather servicioAccuWeather;
  Usuario usuario;

  @BeforeEach
  public void setUp() {
    servicioAccuWeather = Mockito.mock(ServicioAccuWeather.class);
    motorDeSugerenciasClimatico = new MotorDeSugerenciasClimatico(servicioAccuWeather);
    Guardarropa guardarropas = new Guardarropa(
        List.of(unBuzo(), unaCampera(), unaParteInferior(), unCalzado()),
        "c",
        motorDeSugerenciasClimatico);
    usuario = new Usuario(
        20
    );
    usuario.agregarGuardarropa(guardarropas);
  }

  @Test
  void deberiaGenerarTodasLasSugerenciasParaTemperaturaBaja() {
    Mockito.when(servicioAccuWeather.getTemperaturaActual()).thenReturn(temperaturaBaja());
    assertEquals(2, usuario.generarSugerencias("c").size());
  }

  @Test
  void deberiaGenerarTodasLasSugerenciasPosiblesParaTemperaturaAlta() {
    Mockito.when(servicioAccuWeather.getTemperaturaActual()).thenReturn(temperaturaAlta());
    assertEquals(0, usuario.generarSugerencias("c").size());
  }

  private Prenda unBuzo() {
    return new PrendaBorrador(TipoPrenda.BUZO)
        .conMaterial(Material.ALGODON)
        .conColorPrincipal(Color.VERDE)
        .conFormalidad(Formalidad.INFORMAL)
        .construirPrenda();
  }
  private Prenda unaCampera() {
    return new PrendaBorrador(TipoPrenda.CAMPERA)
        .conMaterial(Material.ALGODON)
        .conColorPrincipal(Color.VERDE)
        .conFormalidad(Formalidad.INFORMAL)
        .construirPrenda();
  }

  private Prenda unaParteInferior() {
    return new PrendaBorrador(TipoPrenda.PANTALON)
        .conMaterial(Material.ALGODON)
        .conColorPrincipal(Color.VERDE)
        .conFormalidad(Formalidad.INFORMAL)
        .construirPrenda();
  }

  private Prenda unCalzado() {
    return new PrendaBorrador(TipoPrenda.BOTAS)
        .conMaterial(Material.CUERO)
        .conColorPrincipal(Color.VERDE)
        .conFormalidad(Formalidad.INFORMAL)
        .construirPrenda();
  }

  private Temperatura temperaturaBaja() {
    return new Celsius(15);
  }
  private Temperatura temperaturaAlta() {
    return new Fahrenheit(86);
  }
}