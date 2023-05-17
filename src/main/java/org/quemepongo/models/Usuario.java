package org.quemepongo.models;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    List<Prenda> prendas = new ArrayList<>();
    Integer edad;
    MotorDeSugerencias motorDeSugerencias;

    public Usuario(List<Prenda> prendas, Integer edad, MotorDeSugerencias motorDeSugerencias) {
        this.prendas = prendas;
        this.edad = edad;
        this.motorDeSugerencias = motorDeSugerencias;
    }

    List<Sugerencia> generarSugerencias(){
        return this.motorDeSugerencias.generarSugerencias(this);
    }

    Sugerencia generarSugerencia() {
        return this.motorDeSugerencias.generarSugerencia(this);
    }

    public List<Prenda> getPrendas() {
        return prendas;
    }

    public Integer getEdad() {
        return edad;
    }

    public MotorDeSugerencias getMotorDeSugerencias() {
        return motorDeSugerencias;
    }
}
