package org.quemepongo.utils;

import org.quemepongo.enums.*;
import org.quemepongo.models.Prenda;

public class PrendaBorrador {

  private Trama tramaPorDefecto = Trama.LISA;
  private TipoPrenda tipoPrenda;
  private Trama trama = tramaPorDefecto;
  private Color colorPrincipal;
  private Color colorSecundario;
  private Material material;
  private Formalidad formalidad;

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

  public PrendaBorrador conFormalidad(Formalidad formalidad) {
    this.formalidad = formalidad;
    return this;
  }

  public Prenda construirPrenda() {
    validarCamposRequeridos();
    return new Prenda(tipoPrenda, material, colorPrincipal, colorSecundario, trama, formalidad);
  }

  private void validarCamposRequeridos() {
    if (tipoPrenda == null) {
      throw new IllegalArgumentException("El campo tipo de prenda es requerido");
    }
    if(formalidad == null) {
      throw new IllegalArgumentException("El campo formalidad es requerido");
    }
    if (colorPrincipal == null) {
      throw new IllegalArgumentException("El campo color principal es requerido");
    }
    if (material == null) {
      throw new IllegalArgumentException("El campo material es requerido");
    }
  }

}
