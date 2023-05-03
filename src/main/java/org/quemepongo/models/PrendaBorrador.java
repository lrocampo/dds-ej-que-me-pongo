package org.quemepongo.models;

public class PrendaBorrador {

  private Trama tramaPorDefecto = Trama.LISA;
  private TipoPrenda tipoPrenda;
  private Trama trama = tramaPorDefecto;
  private Color colorPrincipal;
  private Color colorSecundario;
  private Material material;

  public PrendaBorrador(TipoPrenda tipoPrenda) {
    this.tipoPrenda = tipoPrenda;
  }

  public PrendaBorrador conTrama(Trama trama) {
    this.trama = trama != null ? trama : tramaPorDefecto;
    return this;
  }

  public PrendaBorrador conColorPrincipal(Color colorPrincipal) {
    this.colorPrincipal = colorPrincipal;
    return this;
  }

  public PrendaBorrador conColorSecundario(Color colorSecundario) {
    this.colorSecundario = colorSecundario;
    return this;
  }

  public PrendaBorrador conMaterial(Material material) {
    this.material = material;
    return this;
  }

  public Prenda construirPrenda() {
    validarCamposRequeridos();
    return new Prenda(tipoPrenda, material, colorPrincipal, colorSecundario, trama);
  }

  private void validarCamposRequeridos() {
    if (tipoPrenda == null) {
      throw new IllegalArgumentException("El campo tipo de prenda es requerido");
    }
    if (colorPrincipal == null) {
      throw new IllegalArgumentException("El campo color principal es requerido");
    }
    if (material == null) {
      throw new IllegalArgumentException("El campo material es requerido");
    }
  }


}
