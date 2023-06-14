package org.quemepongo.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.quemepongo.exceptions.DomainException;

public class Usuario {
  private List<Guardarropa> guardarropas = new ArrayList<>();
  private List<PropuestaModificacion> propuestas = new ArrayList<>();
  private Integer edad;

  public Usuario(Integer edad) {
    this.edad = edad;
  }

  List<Sugerencia> generarSugerencias(String criterio) {
    return guardarropas.stream()
        .flatMap(guardarropa -> guardarropa.generarSugerencias(this).stream()).toList();
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

  public List<Guardarropa> getGuardarropas() {
    return guardarropas;
  }

  public void agregarGuardarropa(Guardarropa guardarropa) {
    guardarropas.add(guardarropa);
  }

  public List<PropuestaModificacion> getPropuestasPendientes() {
    return propuestas.stream().filter(propuesta -> !propuesta.isAceptada()).toList();
  }

  public void aceptarPropuesta(PropuestaModificacion propuesta) {
    propuesta.aplicar();
  }

  public void rechazarPropuesta(PropuestaModificacion propuesta) {
    propuestas.remove(propuesta);
  }

  public void deshacerPropuesta(PropuestaModificacion propuesta) {
    propuesta.deshacer();
    propuestas.remove(propuesta);
  }

  public void agregarPropuesta(PropuestaModificacion propuesta) {
    propuestas.add(propuesta);
  }

  public void proponerModificacion(PropuestaModificacion propuesta, Usuario usuario) {
    usuario.agregarPropuesta(propuesta);
  }
}
