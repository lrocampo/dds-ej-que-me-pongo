package org.quemepongo.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TemperaturaTest {

  @Test
  void unaTemperaturaEnFarenheitACelsius() {
    Temperatura fahrenheit = new Fahrenheit(41);
    assertEquals(5, fahrenheit.enCelsius().getValor());
  }

  @Test
  void unaTemperaturaEnCelsiusAFahrenheit() {
    Temperatura celsius = new Celsius(5);
    assertEquals(41, celsius.enFahrenheit().getValor());
  }
}