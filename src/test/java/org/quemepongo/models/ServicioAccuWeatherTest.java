package org.quemepongo.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.time.Duration;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.quemepongo.apis.AccuWeatherAPI;
import org.quemepongo.exceptions.DomainException;
import org.quemepongo.services.ServicioAccuWeather;

class ServicioAccuWeatherTest {

  private AccuWeatherAPI accuWeatherAPI;

  @BeforeEach
  public void setUp() {
    accuWeatherAPI = Mockito.mock(AccuWeatherAPI.class);
  }
  @Test
  void deberiaObtenerLaTemperaturaActual() {
    Mockito.when(accuWeatherAPI.getWeather(any())).thenReturn(dataCorrecta());

    ServicioAccuWeather servicioAccuWeather = new ServicioAccuWeather(accuWeatherAPI, Duration.ZERO);

    assertEquals(57, servicioAccuWeather.getTemperaturaActual().getValor());

  }

  @Test
  void cuandoNoHayClimasDisponibles() {
    Mockito.when(accuWeatherAPI.getWeather(any())).thenReturn(List.of());

    ServicioAccuWeather servicioAccuWeather = new ServicioAccuWeather(accuWeatherAPI, Duration.ZERO);

    assertThrows(DomainException.class, () -> servicioAccuWeather.getTemperaturaActual().getValor());

  }

  @Test
  void cuandoHayTemperaturasConUnidadesInvalidas() {
    Mockito.when(accuWeatherAPI.getWeather(any())).thenReturn(dataIncorrecta());

    ServicioAccuWeather servicioAccuWeather = new ServicioAccuWeather(accuWeatherAPI, Duration.ZERO);

    assertThrows(IllegalArgumentException.class, () -> servicioAccuWeather.getTemperaturaActual().getValor());

  }

  private List<Map<String, Object>> dataCorrecta() {
    return Arrays.asList(new HashMap<>() {
      {
        put("PrecipitationProbability", 0);
        put("Temperature", new HashMap<String, Object>() {{
          put("Value", 57);
          put("Unit", "F");
          put("UnitType", 18);
        }});
      }
    });
  }

  private List<Map<String, Object>> dataIncorrecta() {
    return Arrays.asList(new HashMap<>() {
      {
        put("PrecipitationProbability", 0);
        put("Temperature", new HashMap<String, Object>() {{
          put("Value", 57);
          put("Unit", "M");
          put("UnitType", 18);
        }});
      }
    });
  }

}