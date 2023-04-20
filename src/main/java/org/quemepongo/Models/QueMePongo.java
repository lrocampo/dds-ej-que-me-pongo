package org.quemepongo.Models;

import java.util.ArrayList;
import java.util.List;

public class QueMePongo {
    private List<Prenda> prendas = new ArrayList<>();

    public void cargarPrenda(Prenda prenda) {
        prendas.add(prenda);
    }
}
