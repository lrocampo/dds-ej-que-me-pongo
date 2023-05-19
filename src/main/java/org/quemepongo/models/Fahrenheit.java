package org.quemepongo.models;

public class Fahrenheit extends Temperatura{

  public Fahrenheit(double valor) {
    super(valor);
  }

  @Override
  public Fahrenheit enFahrenheit() {
    return this;
  }

  @Override
  public Celsius enCelsius() {
    double conversion = (getValor() - 32) * 5 / 9;
    return new Celsius(conversion);
  }

}
