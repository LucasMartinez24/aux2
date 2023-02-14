package ar.com.edu.unju.edm.usuario;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "notas")
public class UsuarioPregunta {
  @Id
  @GeneratedValue
  (strategy=GenerationType.IDENTITY)
  private Long Id;
  private Integer PuntajeTotal;
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dni")
  private Usuario usuario;
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name ="CodPregunta")
  private Pregunta pregunta;
  private Integer nivel;
  public UsuarioPregunta(Long id, Integer puntajeTotal, Usuario usuario, Pregunta pregunta, Integer nivel) {
    Id = id;
    PuntajeTotal = puntajeTotal;
    this.usuario = usuario;
    this.pregunta = pregunta;
    this.nivel = nivel;
  }
  public UsuarioPregunta() {
  }
  public Long getId() {
    return Id;
  }
  public void setId(Long id) {
    Id = id;
  }
  public Integer getPuntajeTotal() {
    return PuntajeTotal;
  }
  public void setPuntajeTotal(Integer puntajeTotal) {
    PuntajeTotal = puntajeTotal;
  }
  public Usuario getUsuario() {
    return usuario;
  }
  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }
  public Pregunta getPregunta() {
    return pregunta;
  }
  public void setPregunta(Pregunta pregunta) {
    this.pregunta = pregunta;
  }
  public Integer getNivel() {
    return nivel;
  }
  public void setNivel(Integer nivel) {
    this.nivel = nivel;
  }
  
  
}