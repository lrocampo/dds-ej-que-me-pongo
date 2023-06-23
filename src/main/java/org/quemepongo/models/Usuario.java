package org.quemepongo.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.quemepongo.repositories.RepositorioClima;
import org.quemepongo.exceptions.DomainException;

public class Usuario {
  private List<Guardarropa> guardarropas = new ArrayList<>();
  private List<PropuestaModificacion> propuestas = new ArrayList<>();
  private Integer edad;

  private String mail = "dataentry1@gmail.com";

  private List<Accion> acciones = new ArrayList<>();

  public Usuario(List<Guardarropa> guardarropas, Integer edad) {
    this.guardarropas = new ArrayList<>(guardarropas);
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

  List<Sugerencia> generarSugerencias() {
    return guardarropas.stream()
        .flatMap(guardarropa -> guardarropa.generarSugerencias(this).stream())
        .toList();
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
    return propuestas.stream().filter(PropuestaModificacion::isPendiente).toList();
  }

  public List<PropuestaModificacion> getPropuestasAceptadas() {
    return propuestas.stream().filter(PropuestaModificacion::isAceptada).toList();
  }

  void agregarPrendaTentativa(Prenda prenda, Guardarropa guardarropa) {
    propuestas.add(new AgregarPrenda(guardarropa, prenda));
  }

  void removerPrendaTentativa(Prenda prenda, Guardarropa guardarropa) {
    propuestas.add(new QuitarPrenda(guardarropa, prenda));
  }
  public String getMail() {
    return mail;
  }
}
