package org.quemepongo.models;

import java.util.List;

public class MotorDeSugerenciasFormal extends MotorDeSugerencias {

    @Override
    public List<Prenda> getPrendasValidas(Usuario usuario, List<Prenda> prendas) {
        return usuario.getEdad() > 55 ? prendas.stream().filter(Prenda::esFormal).toList() : prendas;
    }
}
