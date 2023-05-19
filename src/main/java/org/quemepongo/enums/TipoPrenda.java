package org.quemepongo.enums;


import static org.quemepongo.enums.Categoria.ACCESORIO;
import static org.quemepongo.enums.Categoria.CALZADO;
import static org.quemepongo.enums.Categoria.PARTE_INFERIOR;
import static org.quemepongo.enums.Categoria.PARTE_SUPERIOR;

import org.quemepongo.models.Temperatura;

public enum TipoPrenda {
  PANTALON(PARTE_INFERIOR, 0, 100),
  REMERA_MANGAS_CORTAS(PARTE_SUPERIOR, 25, 100),
  BOTAS(CALZADO, 0,25),
  PANUELO(ACCESORIO, 0, 100);

  private Categoria categoria;
  private double temperaturaMinima;
  private double temperaturaMaxima;

  TipoPrenda(Categoria categoria, double temperaturaMinima, double temperaturaMaxima) {
    this.categoria = categoria;
    this.temperaturaMinima = temperaturaMinima;
    this.temperaturaMaxima = temperaturaMaxima
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public boolean esApta(Temperatura temperatura) {
    double valor = temperatura.getValor();
    return valor <= temperaturaMaxima && valor >= temperaturaMinima;
  }

}
