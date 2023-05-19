package org.quemepongo.models;

public class Celsius extends Temperatura {

  public Celsius(double valor) {
    super(valor);
  }

  @Override
  public Fahrenheit enFahrenheit() {
    double conversion = (getValor() * 9 / 5) + 32;
    return new Fahrenheit(conversion);
  }

  @Override
  public Celsius enCelsius() {
    return this;
  }

}
