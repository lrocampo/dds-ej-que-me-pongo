package org.quemepongo.services;

import java.util.Map;
import org.quemepongo.apis.AccuWeatherAPI;
import org.quemepongo.exceptions.DomainException;
import org.quemepongo.models.Celsius;
import org.quemepongo.models.CondicionClimatica;
import org.quemepongo.models.Fahrenheit;
import org.quemepongo.models.Temperatura;

public class ServicioAccuWeather implements ServicioClima {
  private AccuWeatherAPI accuWeatherAPI;

  public ServicioAccuWeather(AccuWeatherAPI accuWeatherAPI) {
    this.accuWeatherAPI = accuWeatherAPI;
  }

  public CondicionClimatica getCondicionesClimaticas() {
    String CIUDAD = "Buenos Aires, Argentina";
    return accuWeatherAPI.getWeather(CIUDAD).stream()
        .findFirst()
        .map((condicionClimatica) -> new CondicionClimatica(
            (int) condicionClimatica.get("PrecipitationProbability"),
            parsearTemperatura((Map<String, Object>) condicionClimatica.get("Temperature"))
        ))
        .orElseThrow(() -> new DomainException("No hay temperatura disponible"));
  }

  @Override
  public Temperatura getTemperaturaActual() {
    return getCondicionesClimaticas().getTemperatura();
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
