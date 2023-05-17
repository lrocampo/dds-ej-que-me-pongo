package org.quemepongo.models;

public class ProveedorDeMotor {
    static ProveedorDeMotor INSTANCE = new ProveedorDeMotor();
    private MotorDeSugerencias motor;

    static ProveedorDeMotor getInstance(){
        return INSTANCE;
    }

    public MotorDeSugerencias getMotor(){
        return motor;
    }

    public void setMotor(MotorDeSugerencias motor){
        this.motor = motor;
    }
}
