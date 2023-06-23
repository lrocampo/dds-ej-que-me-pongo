package org.quemepongo.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.quemepongo.repositories.RepositorioClima;
import org.quemepongo.exceptions.DomainException;

public class Usuario {
  private List<Guardarropa> guardarropas;
  private List<Guardarropa> guardarropasCompartido = new ArrayList<>();
  private Integer edad;
  private List<PropuestaModificacion> propuestas = new ArrayList<>();

  private String mail = "dataentry1@gmail.com";

  private List<Accion> acciones = new ArrayList<>();

  public Usuario(List<Guardarropa> guardarropas, Integer edad) {
    this.guardarropas = guardarropas;
    this.edad = edad;
  }

  public List<Sugerencia> getSugerenciaDiaria() {
    return guardarropas.stream()
        .map(guardarropa -> guardarropa.generarSugerenciasDiaria(this))
        .findFirst().orElse(List.of());
  }

  public void recibirAlerta(List<Alerta> alertas) {
    acciones.forEach(accion -> accion.activar(this, alertas));
  }

  public List<Alerta> obtenerUltAlertas() {
    return RepositorioClima.get().consultarAlertas("Buenos Aires");
  }

  List<Sugerencia> generarSugerencias(String criterio) {
    return buscarGuardarropa(criterio)
        .map(guardarropa -> guardarropa.generarSugerencias(this))
        .orElse(new ArrayList<>());
  }

  Sugerencia generarSugerencia() {
    return guardarropas.stream()
        .findAny()
        .map(guardarropa -> guardarropa.generarSugerencia(this))
        .orElseThrow(() -> new DomainException("No hay guardarropas en el usuario"));
  }

  void compartirGuardarropaCon(Usuario usuario, String criterio) {
    buscarGuardarropa(criterio)
        .ifPresentOrElse(usuario::agregarGuardarropaCompartido, () -> {
          throw new DomainException("No hay guardarropas con ese criterio");
        });
  }

  private void agregarGuardarropaCompartido(Guardarropa guardarropa) {
    this.guardarropasCompartido.add(guardarropa);
  }

  Optional<Guardarropa> buscarGuardarropa(String criterio) {
    return guardarropas.stream()
        .filter(guardarropa -> guardarropa.getCriterio().equalsIgnoreCase(criterio))
        .findFirst();
  }

  public Integer getEdad() {
    return edad;
  }

  public List<Guardarropa> getGuardarropas() {
    return guardarropas;
  }

  public List<Guardarropa> getGuardarropasCompartido() {
    return guardarropasCompartido;
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

  public String getMail() {
    return mail;
  }
}
