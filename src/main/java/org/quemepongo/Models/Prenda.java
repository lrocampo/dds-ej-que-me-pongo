package org.quemepongo.Models;

import org.quemepongo.Utils.CampoInvalidoException;
import org.quemepongo.Utils.CampoRequeridoException;

import java.util.*;

public class Prenda {
    TipoPrenda tipoPrenda;
    Categoria categoria;
    Color colorPrincipal;
    Color colorSecundario;
    Material material; // Preguntar al cliente ;) si una prenda puede tener mas de un material

    public Prenda(TipoPrenda tipoPrenda, Categoria categoria, Color colorPrincipal, Material material, Color colorSecundario) {
        Map<String, Object> camposRequeridos = new HashMap<>();

        camposRequeridos.put("tipo de prenda", tipoPrenda);
        camposRequeridos.put("categoria", categoria);
        camposRequeridos.put("color principal", colorPrincipal);
        camposRequeridos.put("material", material);

        camposRequeridos.forEach((nombreCampo, campo ) -> validarRequerido(campo, nombreCampo));

        if(tipoPrenda.perteneceACategoria(categoria)) {
            this.categoria = categoria;
        }
        else throw new CampoInvalidoException("Categoria " + categoria + " invalida para el tipo de prenda " + tipoPrenda);
        this.tipoPrenda = tipoPrenda;
        this.colorPrincipal = colorPrincipal;
        this.material = material;
        this.colorSecundario = colorSecundario;
    }

    public Prenda(TipoPrenda tipoPrenda, Categoria categoria, Color colorPrincipal, Material material) {
        this(tipoPrenda, categoria, colorPrincipal, material, null);
    }


    private void validarRequerido(Object campo, String nombreCampo) {
        if(campo == null) throw new CampoRequeridoException("El campo " + nombreCampo + " es requerido");
    }

}
