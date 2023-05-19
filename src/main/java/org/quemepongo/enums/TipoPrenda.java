package org.quemepongo.enums;


import static org.quemepongo.enums.Categoria.ACCESORIO;
import static org.quemepongo.enums.Categoria.CALZADO;
import static org.quemepongo.enums.Categoria.PARTE_INFERIOR;
import static org.quemepongo.enums.Categoria.PARTE_SUPERIOR;

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
