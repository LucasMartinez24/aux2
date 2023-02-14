package ar.com.edu.unju.edm.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.edu.unju.edm.usuario.Pregunta;

@Repository
public interface PreguntaRepositorio extends JpaRepository<Pregunta,Long>{
}