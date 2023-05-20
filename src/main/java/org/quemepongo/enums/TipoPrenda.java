package org.quemepongo.enums;


import static org.quemepongo.enums.Categoria.ACCESORIO;
import static org.quemepongo.enums.Categoria.CALZADO;
import static org.quemepongo.enums.Categoria.PARTE_INFERIOR;
import static org.quemepongo.enums.Categoria.PARTE_SUPERIOR;

import org.quemepongo.models.Temperatura;

public enum TipoPrenda {
  PANTALON(PARTE_INFERIOR,100),
  REMERA_MANGAS_CORTAS(PARTE_SUPERIOR,100),
  BOTAS(CALZADO,25),
  BUZO(PARTE_SUPERIOR, 20),
  CAMPERA(PARTE_SUPERIOR, 20),
  OJOTAS(CALZADO, 100),
  PANUELO(ACCESORIO,100);

  private Categoria categoria;
  private double temperaturaMaxima;

  TipoPrenda(Categoria categoria, double temperaturaMaxima) {
    this.categoria = categoria;
    this.temperaturaMaxima = temperaturaMaxima;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public boolean esApta(Temperatura temperatura) {
    double valor = temperatura.enCelsius().getValor();
    return valor <= temperaturaMaxima;
  }

}
