package ar.com.edu.unju.edm.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.com.edu.unju.edm.repositorio.UsuarioPreguntaRepositorio;
import ar.com.edu.unju.edm.servicio.PreguntaServicio;
import ar.com.edu.unju.edm.servicio.UsuarioPreguntaServicio;
import ar.com.edu.unju.edm.servicio.UsuarioServicio;
import ar.com.edu.unju.edm.usuario.Pregunta;
import ar.com.edu.unju.edm.usuario.Usuario;
import ar.com.edu.unju.edm.usuario.UsuarioPregunta;

@Controller
public class PreguntasControlador {
  @Autowired
  PreguntaServicio preguntaServicio;
  @Autowired
  UsuarioServicio usuarioServicio;
  @Autowired
  UsuarioPreguntaServicio usuarioPreguntaServicio;
  @Autowired
  UsuarioPregunta usuarioPregunta;
  @Autowired
  UsuarioPreguntaRepositorio usuarioPreguntaRepositorio;
  @GetMapping({ "/preguntas/nuevo" })
	public String crearPregunta(Model modelo) {
		Pregunta pregunta=new Pregunta(); 
		modelo.addAttribute("pregunta", pregunta);
		return "crear_preguntas";
	}
  @PostMapping("/preguntas/nuevo")
	public String guardarPregunta(@ModelAttribute("pregunta") Pregunta pregunta) {
		preguntaServicio.guardarPregunta(pregunta);
		return "redirect:/tablaPreguntas";
	}
  @GetMapping("/tablaPreguntas")
  public String Tabla(Model model){
    model.addAttribute("preguntas1",preguntaServicio.listarPreguntasPorNivel(1) );
    model.addAttribute("preguntas2", preguntaServicio.listarPreguntasPorNivel(2));
    return "tabla_preguntas";
  }
  @GetMapping("/preguntas/editar/{id}")
	public String editarPregunta(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("pregunta", preguntaServicio.obtenerPreguntaporId(id));
		return "editar_usuario"; 
	}

	@PostMapping("/preguntas/editar/{id}")
	public String actualizarPregunta(@PathVariable Long id, @ModelAttribute("pregunta") Pregunta pregunta, Model modelo) {
		preguntaServicio.actualizarPregunta(pregunta);
		return "redirect:/tablaPreguntas";
	}
  @GetMapping("/elegirNivel")
  public ModelAndView ElegirNivel() {
    Authentication auth = SecurityContextHolder
        .getContext()
        .getAuthentication();
    UserDetails userDetail = (UserDetails) auth.getPrincipal();
    Usuario userEnSesion = usuarioServicio.obtenerUsuarioporId(Long.parseLong(userDetail.getUsername()));
    ModelAndView vista = new ModelAndView("elegirnivel");
    vista.addObject("sesion", userEnSesion);
    return vista;
  }

  @GetMapping("/nivel1/{nv}")
  public ModelAndView Nivel1(@PathVariable(name = "nv") Integer id) {
    UsuarioPregunta aux = new UsuarioPregunta();
    Authentication auth = SecurityContextHolder
        .getContext()
        .getAuthentication();
    UserDetails userDetail = (UserDetails) auth.getPrincipal();
    Usuario userEnSesion = usuarioServicio.obtenerUsuarioporId(Long.parseLong(userDetail.getUsername()));
    if(id==1){
      usuarioPreguntaServicio.reinicioPuntaje(userEnSesion.getDni(), 1);
    }
    userEnSesion.setPuntajenv1(0);
    usuarioServicio.actualizarUsuario(userEnSesion);
    aux.setNivel(1);
    aux.setUsuario(userEnSesion);
    aux.setPregunta(preguntaServicio.buscarPregunta(1, id));
    ModelAndView vista = new ModelAndView("pregunta1");
    vista.addObject("nro",id);
    vista.addObject("pregunta", aux.getPregunta());
    vista.addObject("puntaje", aux);
    return vista;
  }

  @PostMapping("/subirpuntaje/{nv}")
  public String subirnivel(@ModelAttribute("puntaje") UsuarioPregunta puntaje, @PathVariable(name = "nv") Integer id) {
    System.out.println("xd"+id);
    if(id<=5){
    usuarioPreguntaServicio.guardarPregunta(puntaje);
      return "redirect:/nivel1/{nv}";
    }else{
      usuarioPreguntaServicio.guardarPregunta(puntaje);
    return "redirect:/vernota1";
    }
  }

  @GetMapping("/nivel2")
  public ModelAndView Nivel2() {
    ModelAndView vista = new ModelAndView("nivel2");
    vista.addObject("preguntas", preguntaServicio.listarPreguntasPorNivel(2));
    return vista;
  }

}