package org.quemepongo.models;

import java.util.List;

public class MotorDeSugerenciasBasico extends MotorDeSugerencias{
    @Override
    public List<Prenda> getPrendasValidas(Usuario usuario, List<Prenda> prendas) {
        return prendas;
    }
}
