package org.quemepongo.repositories;

import java.util.ArrayList;
import java.util.List;
import org.quemepongo.models.Usuario;

public class RepositorioUsuarios {
  private static final RepositorioUsuarios instance = new RepositorioUsuarios();
  private List<Usuario> usuarios = new ArrayList<>();

  private RepositorioUsuarios() {}
  public static RepositorioUsuarios get() { return instance; }
  public List<Usuario> getUsuarios() { return usuarios; }

  public void enviarSugerenciaDiaria() {
    usuarios.forEach(Usuario::getSugerenciaDiaria);
  }

  public void agregarUsuario(Usuario usuario) { usuarios.add(usuario); }
  public void quitarUsuario(Usuario usuario) { usuarios.remove(usuario); }

}
