package org.quemepongo.models;

import java.util.List;

public class MotorDeSugerenciasFormal extends MotorDeSugerencias {

    @Override
    public List<Prenda> getPrendasValidas(Usuario usuario) {
        return usuario.getEdad() > 55 ? usuario.getPrendas().stream().filter(Prenda::esFormal).toList() : usuario.getPrendas();
    }
}
