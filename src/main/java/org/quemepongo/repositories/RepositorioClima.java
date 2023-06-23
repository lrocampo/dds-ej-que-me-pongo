package org.quemepongo.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.quemepongo.models.Alerta;
import org.quemepongo.models.CondicionClimatica;
import org.quemepongo.services.ServicioClima;

public class RepositorioClima {
  private static final RepositorioClima instance = new RepositorioClima();
  private ServicioClima servicioMeteorologico;
  private Map<String, List<Alerta>> alertasActuales = new HashMap<>();

  private RepositorioClima() {}
  public static RepositorioClima get() { return instance; }

  public CondicionClimatica consultarClima() {
    return servicioMeteorologico.obtenerCondicionesClimaticas();
  }

  public void actualizarAlertas() {
    RepositorioUsuarios.get().getUsuarios().forEach(usuario -> {
      String direccion = "Buenos Aires";
      alertasActuales.put(direccion, servicioMeteorologico.obtenerAlertas(direccion));
      if(!alertasActuales.get(direccion).isEmpty())
        usuario.recibirAlerta(alertasActuales.get(direccion));
    });
  }

  public List<Alerta> consultarAlertas(String direccion) {
    return alertasActuales.get(direccion);
  }

  public void setServicioMeteorologico(ServicioClima servicioMeteorologico) {
    this.servicioMeteorologico = servicioMeteorologico;
  }

}