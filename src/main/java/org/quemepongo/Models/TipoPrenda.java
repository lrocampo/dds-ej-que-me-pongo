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

    public boolean perteneceACategoria(Categoria categoria) {
        return switch (this) {
            case REMERA_MANGAS_CORTAS, MUSCULOSA -> categoria == PARTE_SUPERIOR;
            case PANTALON, POLLERA -> categoria == PARTE_INFERIOR;
            case ZAPATOS, ZAPATILLAS, BOTAS, CROCS -> categoria == CALZADO;
            case PANUELO, ANTEOJOS -> categoria == ACCESORIOS;
            default -> false;
        };
    }
}
