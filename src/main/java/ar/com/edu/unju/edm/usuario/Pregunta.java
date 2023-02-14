package ar.com.edu.unju.edm.usuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "pregunta")
public class Pregunta {
  @Id
  @GeneratedValue
  (strategy=GenerationType.IDENTITY)
  private Long CodPregunta;
  private String Enunciado;
  private Integer Nivel;
  private String Opcion01;
  private String Opcion02;
  private String Opcion03;
  private String OpcionCorrecta;
  private Integer Puntaje;
  private Boolean estado;
  public Pregunta() {
    super();
  }
  public Pregunta(Long codPregunta, String enunciado, Integer nivel, String opcion01, String opcion02, String opcion03, String opcionCorrecta, Integer puntaje,Boolean estado) {
    CodPregunta = codPregunta;
    Enunciado = enunciado;
    Nivel = nivel;
    Opcion01 = opcion01;
    Opcion02 = opcion02;
    Opcion03 = opcion03;
    OpcionCorrecta = opcionCorrecta;
    Puntaje = puntaje;
    this.estado = estado;
  }
  public Long getCodPregunta() {
    return CodPregunta;
  }
  public void setCodPregunta(Long codPregunta) {
    CodPregunta = codPregunta;
  }
  public String getEnunciado() {
    return Enunciado;
  }
  public void setEnunciado(String enunciado) {
    Enunciado = enunciado;
  }
  public Integer getNivel() {
    return Nivel;
  }
  public void setNivel(Integer nivel) {
    Nivel = nivel;
  }
  public String getOpcion01() {
    return Opcion01;
  }
  public void setOpcion01(String opcion01) {
    Opcion01 = opcion01;
  }
  public String getOpcion02() {
    return Opcion02;
  }
  public void setOpcion02(String opcion02) {
    Opcion02 = opcion02;
  }
  public String getOpcion03() {
    return Opcion03;
  }
  public void setOpcion03(String opcion03) {
    Opcion03 = opcion03;
  }
  public String getOpcionCorrecta() {
    return OpcionCorrecta;
  }
  public void setOpcionCorrecta(String opcionCorrecta) {
    OpcionCorrecta = opcionCorrecta;
  }
  public Integer getPuntaje() {
    return Puntaje;
  }
  public void setPuntaje(Integer puntaje) {
    Puntaje = puntaje;
  }
  public Boolean getEstado() {
    return estado;
  }
  public void setEstado(Boolean estado) {
    this.estado = estado;
  }
}