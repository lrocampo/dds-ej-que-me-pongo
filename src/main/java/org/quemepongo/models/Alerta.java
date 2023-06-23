package org.quemepongo.models;

public enum Alerta {

  STORM {
    @Override
    public String mensaje() { return "Alerta tormenta"; }
  },
  HAIL {
    @Override
    public String mensaje() { return "Alerta granizo"; }
  };

  public abstract String mensaje();
}
