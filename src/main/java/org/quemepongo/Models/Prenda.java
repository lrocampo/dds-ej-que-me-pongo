package org.quemepongo.Models;

public class Prenda {
  protected TipoPrenda tipoPrenda;
  protected Trama trama = Trama.LISA;
  protected Color colorPrincipal;
  protected Color colorSecundario;
  protected Material material;

  public Prenda(TipoPrenda tipoPrenda, Material material, Color colorPrincipal, Color colorSecundario, Trama trama) {
    validarCampos(tipoPrenda, material, colorPrincipal);
    this.tipoPrenda = tipoPrenda;
    this.colorPrincipal = colorPrincipal;
    this.material = material;
    this.colorSecundario = colorSecundario;
    if (trama != null) this.trama = trama;
  }

  protected Prenda() {
  }

  private void validarCampos(TipoPrenda tipoPrenda, Material material, Color colorPrincipal) {
    if (tipoPrenda == null) throw new IllegalArgumentException("El campo tipo de prenda es requerido");
    if (colorPrincipal == null) throw new IllegalArgumentException("El campo color principal es requerido");
    if (material == null) throw new IllegalArgumentException("El campo material es requerido");
  }

  public Categoria getCategoria() {
    return tipoPrenda.getCategoria();
  }

  public Boolean esSuperior() {
    return tipoPrenda.getCategoria() == Categoria.PARTE_SUPERIOR;
  }

  public Boolean esInferior() {
    return tipoPrenda.getCategoria() == Categoria.PARTE_INFERIOR;
  }

  public Boolean esCalzado() {
    return tipoPrenda.getCategoria() == Categoria.CALZADO;
  }

  public Boolean esAccesorio() {
    return tipoPrenda.getCategoria() == Categoria.ACCESORIO;
  }

  public Trama getTrama() {
    return trama;
  }
}
