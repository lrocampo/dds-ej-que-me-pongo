package org.quemepongo.services;

import static org.quemepongo.models.Humedad.LLUVIOSO;
import static org.quemepongo.models.Humedad.SECO;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.quemepongo.models.RespuestaMeteorologica;
import org.quemepongo.apis.AccuWeatherAPI;
import org.quemepongo.exceptions.DomainException;
import org.quemepongo.models.*;

public class ServicioAccuWeather implements ServicioClima {
  private AccuWeatherAPI accuWeatherAPI;
  private HashMap<String, RespuestaMeteorologica> ultimasRespuestas = new HashMap<String, RespuestaMeteorologica>();
  private final Duration periodoDeValidez;

  private String direccion = "Buenos Aires";

  public ServicioAccuWeather(AccuWeatherAPI accuWeatherAPI, Duration periodoDeValidez) {
    this.accuWeatherAPI = accuWeatherAPI;
    this.periodoDeValidez = periodoDeValidez;
  }

  public CondicionClimatica getCondicionesClimaticas(String direccion) {
    String CIUDAD = "Buenos Aires, Argentina";
    return accuWeatherAPI.getWeather(CIUDAD).stream()
        .findFirst()
        .map((condicionClimatica) -> new CondicionClimatica(
            (Double.parseDouble(condicionClimatica.get("PrecipitationProbability").toString()) > 0.8) ? LLUVIOSO : SECO,
            parsearTemperatura((Map<String, Object>) condicionClimatica.get("Temperature"))
        ))
        .orElseThrow(() -> new DomainException("No hay temperatura disponible"));
  }

  @Override
  public Temperatura getTemperaturaActual() {
    return getCondicionesClimaticas(direccion).getTemperatura();
  }

  @Override
  public List<Alerta> obtenerAlertas(String direccion) {
    Map<String, Object> alertas = accuWeatherAPI.getAlertas("Buenos Aires");
    return (List<Alerta>) alertas.get("CurrentAlerts");
  }

  @Override
  public CondicionClimatica obtenerCondicionesClimaticas() {
    if (!ultimasRespuestas.containsKey(direccion) || ultimasRespuestas.get(direccion).expiro()) {
      LocalDateTime expiracion = LocalDateTime.now().plus(periodoDeValidez);
      ultimasRespuestas.put(direccion,
          new RespuestaMeteorologica(getCondicionesClimaticas(direccion), expiracion));
    }
    return ultimasRespuestas.get(direccion).getClima();
  }

  private Temperatura parsearTemperatura(Map<String, Object> temperatura) {
    double valor = (double) (int) temperatura.get("Value");
    String unidad = (String) temperatura.get("Unit");
    if (unidad.equals("F")) {
      return new Fahrenheit(valor);
    } else if (unidad.equals("C")) {
      return new Celsius(valor);
    } else {
      throw new IllegalArgumentException("Unidad de temperatura no parseable");
    }
  }
}
