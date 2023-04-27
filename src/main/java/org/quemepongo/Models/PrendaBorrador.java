package org.quemepongo.Models;

public class PrendaBorrador extends Prenda {

  public PrendaBorrador(Prenda prenda) {
    super(prenda.tipoPrenda, prenda.material, prenda.colorPrincipal, prenda.colorSecundario, prenda.trama);
  }

  public PrendaBorrador() {
  }

  public void setTipoPrenda(TipoPrenda tipoPrenda) {
    this.tipoPrenda = tipoPrenda;
  }

  public void setTrama(Trama trama) {
    this.trama = trama;
  }

  public void setColorPrincipal(Color colorPrincipal) {
    this.colorPrincipal = colorPrincipal;
  }

  public void setColorSecundario(Color colorSecundario) {
    this.colorSecundario = colorSecundario;
  }

  public void setMaterial(Material material) {
    this.material = material;
  }

  public Prenda finalizarBorrador() {
    return new Prenda(tipoPrenda, material, colorPrincipal, colorSecundario, trama);
  }

}
