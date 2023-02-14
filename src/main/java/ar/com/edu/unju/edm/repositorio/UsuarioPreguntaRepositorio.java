package ar.com.edu.unju.edm.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.edu.unju.edm.usuario.UsuarioPregunta;

public interface UsuarioPreguntaRepositorio extends JpaRepository<UsuarioPregunta,Long>{
  
}