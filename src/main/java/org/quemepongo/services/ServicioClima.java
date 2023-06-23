package org.quemepongo.services;

import java.util.List;
import org.quemepongo.models.Alerta;
import org.quemepongo.models.CondicionClimatica;
import org.quemepongo.models.Temperatura;

public interface ServicioClima {
  Temperatura getTemperaturaActual();

  List<Alerta> obtenerAlertas(String direccion);

  CondicionClimatica obtenerCondicionesClimaticas();
}
