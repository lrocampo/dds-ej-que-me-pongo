package org.quemepongo.Models;

import static org.quemepongo.Models.Categoria.*;

public enum TipoPrenda {
  PANTALON(PARTE_INFERIOR),
  REMERA_MANGAS_CORTAS(PARTE_SUPERIOR),
  BOTAS(CALZADO),
  PANUELO(ACCESORIO);

  private Categoria categoria;

  TipoPrenda(Categoria categoria) {
    this.categoria = categoria;
  }

  public Categoria getCategoria() {
    return categoria;
  }
}
