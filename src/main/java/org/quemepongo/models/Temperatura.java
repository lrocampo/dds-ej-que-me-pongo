package org.quemepongo.models;

public abstract class Temperatura {
  private double valor;

  public Temperatura(double valor) {
    this.valor = valor;
  }

  public abstract Fahrenheit enFahrenheit();
  public abstract Celsius enCelsius();

  public double getValor() {
    return valor;
  }

}
