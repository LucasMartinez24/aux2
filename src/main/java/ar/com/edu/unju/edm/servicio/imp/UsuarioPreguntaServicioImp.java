package ar.com.edu.unju.edm.servicio.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.edu.unju.edm.repositorio.UsuarioPreguntaRepositorio;
import ar.com.edu.unju.edm.servicio.UsuarioPreguntaServicio;
import ar.com.edu.unju.edm.usuario.UsuarioPregunta;
@Service
public class UsuarioPreguntaServicioImp implements UsuarioPreguntaServicio{
  @Autowired
  UsuarioPreguntaRepositorio repositorio;
  @Override
  public Integer SumarPuntaje(List<UsuarioPregunta> puntajes) {
    Integer aux=0;
    for (int i = 0; i < puntajes.size(); i++) {
      aux=aux+puntajes.get(i).getPuntajeTotal();
    }
    return aux;
  }

  @Override
  public void guardarPregunta(UsuarioPregunta puntaje) {
		repositorio.save(puntaje);
  }

  @Override
  public List<UsuarioPregunta> buscarPorIdUsuario(Long id,Integer nivel) {
    List<UsuarioPregunta> auxiliar = new ArrayList<>();
    List<UsuarioPregunta> aux2=new ArrayList<>();
    auxiliar=(List<UsuarioPregunta>) repositorio.findAll();
    for (int i = 0; i < auxiliar.size(); i++) {
      if(auxiliar.get(i).getUsuario().getDni()==id && auxiliar.get(i).getNivel().equals(nivel)){
         aux2.add(auxiliar.get(i));
      }
    }
    return aux2;
  }

  @Override
  public void reinicioPuntaje(Long id, Integer nivel) {
    List<UsuarioPregunta> auxiliar = new ArrayList<>();
    auxiliar=(List<UsuarioPregunta>) repositorio.findAll();
    for (int i = 0; i < auxiliar.size(); i++) {
      if(auxiliar.get(i).getUsuario().getDni()==id && auxiliar.get(i).getNivel().equals(nivel)){
        auxiliar.get(i).setPuntajeTotal(0);
        repositorio.save(auxiliar.get(i));
      }
    }
  }
  
}