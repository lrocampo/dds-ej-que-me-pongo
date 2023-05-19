package org.quemepongo.models;

import java.util.Map;
import org.quemepongo.utils.DomainException;

public class ServicioAccuWeather implements ServicioClima {
  private AccuWeatherAPI accuWeatherAPI;
  private String CIUDAD = "Buenos Aires, Argentina";

  public ServicioAccuWeather(AccuWeatherAPI accuWeatherAPI) {
    this.accuWeatherAPI = accuWeatherAPI;
  }

  private Map<String, Object> getCondicionesClimaticas() {
    return accuWeatherAPI.getWeather(CIUDAD).stream()
        .findFirst()
        .orElseThrow(() -> new DomainException("No hay temperatura disponible"));
  }

  @Override
  public Temperatura getTemperaturaActual() {
    return parsearTemperatura((Map<String, Object>) getCondicionesClimaticas().get("Temperature"));
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
