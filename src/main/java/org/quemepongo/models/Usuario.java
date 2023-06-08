package org.quemepongo.models;

import java.util.ArrayList;
import java.util.List;
import org.quemepongo.exceptions.DomainException;

public class Usuario {
    List<Guardarropas> guardarropas = new ArrayList<>();
    Integer edad;

    public Usuario(List<Guardarropas> guardarropas, Integer edad) {
        this.guardarropas = guardarropas;
        this.edad = edad;
    }

    List<Sugerencia> generarSugerencias(String criterio){
        return guardarropas.stream()
            .filter(guardarropa -> guardarropa.getCriterio().equalsIgnoreCase(criterio))
            .findFirst()
            .map(guardarropa -> guardarropa.generarSugerencias(this))
            .orElse(new ArrayList<>());
    }

    Sugerencia generarSugerencia() {
        return guardarropas.stream()
            .findAny()
            .map(guardarropa -> guardarropa.generarSugerencia(this))
            .orElseThrow(() -> new DomainException("No hay guardarropas en el usuario"));
    }

    public Integer getEdad() {
        return edad;
    }

}
