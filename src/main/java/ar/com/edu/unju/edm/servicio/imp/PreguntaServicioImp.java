package ar.com.edu.unju.edm.servicio.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.edu.unju.edm.repositorio.PreguntaRepositorio;
import ar.com.edu.unju.edm.servicio.PreguntaServicio;
import ar.com.edu.unju.edm.usuario.Pregunta;

@Service
public class PreguntaServicioImp implements PreguntaServicio {
  @Autowired
  PreguntaRepositorio preguntaRepositorio;

  @Override
  public List<Pregunta> listarPreguntasPorNivel(Integer nivel) {
    List<Pregunta> auxiliar = new ArrayList<>();
    List<Pregunta> aux2=new ArrayList<>();
    auxiliar=(List<Pregunta>) preguntaRepositorio.findAll();
    for (int i = 0; i < auxiliar.size(); i++) {
      if(auxiliar.get(i).getNivel().equals(nivel)){
         aux2.add(auxiliar.get(i));
      }
    }
    return aux2;
  }

  @Override
  public Pregunta buscarPregunta(Integer nivel, int i) {
    List<Pregunta> preguntas= listarPreguntasPorNivel(nivel);
    Pregunta aux=null;
    for (int j = 0; j < i; j++) {
      aux=preguntas.get(j);
    }
    return aux;
  }

  @Override
  public void guardarPregunta(Pregunta pregunta) {
		pregunta.setEstado(true);
		preguntaRepositorio.save(pregunta);
  }

  @Override
  public Pregunta actualizarPregunta(Pregunta pregunta) {
    return preguntaRepositorio.save(pregunta);
  }
  @Override
	public Pregunta obtenerPreguntaporId(Long id) {
		return preguntaRepositorio.findById(id).get();
	}
}
