package org.quemepongo.models;

import com.google.common.collect.Lists;
import org.quemepongo.exceptions.DomainException;

import java.util.List;

public abstract class MotorDeSugerencias {

  public abstract List<Prenda> getPrendasValidas(Usuario usuario);
  public List<Sugerencia> generarSugerencias(Usuario usuario) {
    List<Prenda> prendasSuperiores = getPrendasValidas(usuario).stream().filter(Prenda::esSuperior).toList();
    List<Prenda> prendasInferiores = getPrendasValidas(usuario).stream().filter(Prenda::esInferior).toList();
    List<Prenda> calzados = getPrendasValidas(usuario).stream().filter(Prenda::esCalzado).toList();
    List<List<Prenda>> combinaciones = Lists.cartesianProduct(List.of(prendasSuperiores, prendasInferiores, calzados));
    return combinaciones.stream().map(c -> new Sugerencia(c.get(0), c.get(1), c.get(2))).toList();
  }

  public Sugerencia generarSugerencia(Usuario usuario) {
    return generarSugerencias(usuario).stream().findAny().
            orElseThrow(() -> new DomainException("No hay prendas suficientes para generar una sugerencia"));
  }
}
