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

import ar.com.edu.unju.edm.servicio.UsuarioPreguntaServicio;
import ar.com.edu.unju.edm.servicio.UsuarioServicio;
import ar.com.edu.unju.edm.usuario.Usuario;

@Controller
public class UsuarioControlador {

	@Autowired
	UsuarioServicio servicio;
	@Autowired
	Usuario usuario;
	@Autowired
	UsuarioPreguntaServicio usuarioPreguntaServicio;
	@GetMapping("/login")
  public ModelAndView getlogin(){
    ModelAndView vista= new ModelAndView("index");
    return vista;
  }
	@GetMapping("/")
	public String index(){
		return "index";

	}
	@GetMapping({"/listarusuarios"})
	public String listarUsuarios(Model modelo) {
		modelo.addAttribute("usuarios", servicio.listarTodosLosUsuarios());
		return "usuarios";
	}

	@GetMapping({ "usuarios/nuevo" })
	public String crearUsuarioFormulario(Model modelo) {
		Usuario usuario = new Usuario();
		modelo.addAttribute("usuario", usuario);
		return "crear_usuarios";
	}

	@PostMapping("/usuarios")
	public String guardarUsuarios(@ModelAttribute("usuario") Usuario usuario) {
		servicio.guardarUsuario(usuario);
		return "redirect:/";
	}

	@GetMapping("/usuarios/editar/{id}")
	public String mostrarFormularioDeEditar(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("usuario", servicio.obtenerUsuarioporId(id));
		return "editar_usuario"; 
	}

	@PostMapping("/usuarios/{id}")
	public String actualizarUsuarios(@PathVariable Long id, @ModelAttribute("usuario") Usuario usuario, Model modelo) {
		Usuario usuarioExistente = servicio.obtenerUsuarioporId(id);
		usuarioExistente.setDni(id);
		usuarioExistente.setNombre(usuario.getNombre());
		usuarioExistente.setApellido(usuario.getApellido());
		servicio.actualizarUsuario(usuarioExistente);
		return "redirect:/listarusuarios";
	}

	@GetMapping("/usuarios/eliminar/{id}")
	public String eliminarUsuario(@PathVariable Long id) {
		servicio.eliminarUsuario(id);
		System.out.println("Eliminado");
		return "redirect:/listarusuarios";
	}

	@GetMapping("/vernota1")
	public String vernota(Model modelo) {
		Authentication auth = SecurityContextHolder
        .getContext()
        .getAuthentication();
    UserDetails userDetail = (UserDetails) auth.getPrincipal();
		modelo.addAttribute("puntaje", usuarioPreguntaServicio.SumarPuntaje(usuarioPreguntaServicio.buscarPorIdUsuario(Long.parseLong(userDetail.getUsername()), 1)));
		return "resultados"; 
	}
	@GetMapping("/vernota2")
	public String vernota2(Model modelo) {
		Authentication auth = SecurityContextHolder
        .getContext()
        .getAuthentication();
    UserDetails userDetail = (UserDetails) auth.getPrincipal();
		modelo.addAttribute("puntaje", usuarioPreguntaServicio.SumarPuntaje(usuarioPreguntaServicio.buscarPorIdUsuario(Long.parseLong(userDetail.getUsername()), 2)));
		return "resultados"; 
	}
	@GetMapping("/vernota/{id}")
	public String vernotadocente(Model modelo,@PathVariable Long id) {
		Usuario aux=new Usuario();
		aux=servicio.obtenerUsuarioporId(id);
		modelo.addAttribute("puntaje", usuarioPreguntaServicio.SumarPuntaje(usuarioPreguntaServicio.buscarPorIdUsuario(aux.getDni(), 1)));
		return "resultados_docente"; 
	}
}