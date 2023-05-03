package org.quemepongo.models;

public class Prenda {
  private TipoPrenda tipoPrenda;
  private Trama trama;
  private Color colorPrincipal;
  private Color colorSecundario;
  private Material material;

  public Prenda(TipoPrenda tipoPrenda,
                Material material,
                Color colorPrincipal,
                Color colorSecundario,
                Trama trama) {
    this.tipoPrenda = tipoPrenda;
    this.colorPrincipal = colorPrincipal;
    this.material = material;
    this.colorSecundario = colorSecundario;
    this.trama = trama;
  }

  protected Prenda() {
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