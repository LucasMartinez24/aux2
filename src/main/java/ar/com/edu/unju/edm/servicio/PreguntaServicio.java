package ar.com.edu.unju.edm.servicio;

import java.util.List;

import ar.com.edu.unju.edm.usuario.Pregunta;

public interface PreguntaServicio {
  public List<Pregunta> listarPreguntasPorNivel(Integer nivel); 
  public Pregunta buscarPregunta(Integer nivel,int i);
	public void guardarPregunta(Pregunta pregunta);
	public Pregunta actualizarPregunta(Pregunta pregunta);
  public Pregunta obtenerPreguntaporId(Long id);
}