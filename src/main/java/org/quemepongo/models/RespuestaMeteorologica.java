package org.quemepongo.models;

import java.time.LocalDateTime;
import org.quemepongo.models.Clima;
import org.quemepongo.models.CondicionClimatica;

public class RespuestaMeteorologica {
  private CondicionClimatica clima;
  private LocalDateTime expiracion;

  public RespuestaMeteorologica(CondicionClimatica clima, LocalDateTime expiracion) {
    this.clima = clima;
    this.expiracion = expiracion;
  }

  public boolean expiro() {
    return expiracion.isBefore(LocalDateTime.now());
  }

  public CondicionClimatica getClima() { return clima; }
}