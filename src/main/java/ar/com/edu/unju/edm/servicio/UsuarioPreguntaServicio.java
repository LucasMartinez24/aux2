package ar.com.edu.unju.edm.servicio;

import java.util.List;

import ar.com.edu.unju.edm.usuario.UsuarioPregunta;

public interface UsuarioPreguntaServicio {
  public List<UsuarioPregunta> buscarPorIdUsuario(Long id,Integer nivel);
  public Integer SumarPuntaje(List<UsuarioPregunta> puntaje);
  public void guardarPregunta(UsuarioPregunta puntaje);
  public void reinicioPuntaje(Long id,Integer nivel);
}