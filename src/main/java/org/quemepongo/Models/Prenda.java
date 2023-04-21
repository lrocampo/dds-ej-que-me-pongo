package org.quemepongo.Models;

import org.quemepongo.Utils.CampoRequeridoException;

import java.util.*;

public class Prenda {
  private TipoPrenda tipoPrenda;
  private Color colorPrincipal;
  private Color colorSecundario;
  private Material material; // Preguntar al cliente ;) si una prenda puede tener mas de un material

  public Prenda(TipoPrenda tipoPrenda, Material material, Color colorPrincipal, Color colorSecundario) {
    Map<String, Object> camposRequeridos = new HashMap<>();

    camposRequeridos.put("tipo de prenda", tipoPrenda);
    camposRequeridos.put("color principal", colorPrincipal);
    camposRequeridos.put("material", material);

    camposRequeridos.forEach((nombreCampo, campo) -> validarRequerido(campo, nombreCampo));

    this.tipoPrenda = tipoPrenda;
    this.colorPrincipal = colorPrincipal;
    this.material = material;
    this.colorSecundario = colorSecundario;
  }

  public Prenda(TipoPrenda tipoPrenda, Material material, Color colorPrincipal) {
    this(tipoPrenda, material, colorPrincipal, null);
  }

  private void validarRequerido(Object campo, String nombreCampo) {
    if (campo == null) throw new CampoRequeridoException("El campo " + nombreCampo + " es requerido");
  }

  public Categoria getCategoria() {
    return tipoPrenda.getCategoria();
  }
}
