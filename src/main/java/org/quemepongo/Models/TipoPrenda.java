package org.quemepongo.Models;

import static org.quemepongo.Models.Categoria.*;

public enum TipoPrenda {
  ZAPATOS,
  PANTALON,
  REMERA_MANGAS_CORTAS,
  MUSCULOSA,
  ANTEOJOS,
  PANUELO,
  BOTAS,
  POLLERA,
  ZAPATILLAS,
  CROCS;

  public Categoria getCategoria() {
    return switch (this) {
      case REMERA_MANGAS_CORTAS, MUSCULOSA -> PARTE_SUPERIOR;
      case PANTALON, POLLERA -> PARTE_INFERIOR;
      case ZAPATOS, ZAPATILLAS, BOTAS, CROCS -> CALZADO;
      case PANUELO, ANTEOJOS -> ACCESORIOS;
    };
  }
}
