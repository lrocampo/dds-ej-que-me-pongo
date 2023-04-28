package org.quemepongo.Models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QueMePongo {
  private List<Prenda> prendas = new ArrayList<>();

  // Si bien dice uniformes, cual es la diferencia entre un atuendo y un uniforme?
  // comportamiento diferencial?
  private Set<Atuendo> sugerencias = new HashSet<>();
  private PrendaBorrador prendaBorrador = new PrendaBorrador();

  // Me pareceria sacar la ultima prenda de prendas, y usarla como borrador. Ahora, si me cargan
  // otra prenda atras y perdi la referencia de mi borrador,
  // pierdo la prenda que estaba cargada y que era mi borrador

  public void cargarPrenda(Prenda prenda) {
    prendas.add(prenda);
  }
  /*  CONSULTAR  (tiene que ser la ultima prenda cargada? como tener la chance de modificar la ultima?) */
  //public PrendaBorrador getPrendaBorrador() {
  //  return prendas.size() > 0 ? new PrendaBorrador(prendas.remove(prendas.size() - 1)) : new PrendaBorrador();
  //}

  /*  CONSULTAR  (ó puede ser un borrador que creo y se guarda la ref hasta que decido convertirlo en prenda?) */
  public PrendaBorrador getPrendaBorrador() {
    return prendaBorrador;
  }

  /*  CONSULTAR  (si cargo de esta manera, no corro el riesgo de quedar con el sistema inconsistente?
      ponele q me meten una prenda en el medio)*/

//  public void cargarDesdeBorrador(PrendaBorrador prendaBorrador) {
//    prendas.remove(prendas.size() - 1);
//    cargarPrenda(prendaBorrador.finalizarBorrador());
//  }

  /*  CONSULTAR  (idem el de arriba. En ambos casos obtengo un borrador a partir de la ultima.
    La remuevo asi no se me repite y despues de terminar el borrador la cargo.
    Pero corro el riesgo de perder lo que estaba cargando si alguien me mete una prenda en el medio)*/

//  public PrendaBorrador continuarPrenda() {
//    if(prendas.size() == 0) throw new RuntimeException("No tenes prenda para continuar");
//    return new PrendaBorrador(prendas.remove(prendas.size() - 1));
//  }

  public void cargarSugerencia(Atuendo uniforme) {
    sugerencias.add(uniforme);
  }

}
