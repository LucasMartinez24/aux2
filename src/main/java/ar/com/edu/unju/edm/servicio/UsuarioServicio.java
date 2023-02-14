package ar.com.edu.unju.edm.servicio;

import java.util.List;

import ar.com.edu.unju.edm.usuario.Usuario;

public interface UsuarioServicio {
	public List<Usuario> listarTodosLosUsuarios();
	public void guardarUsuario(Usuario usuario);
	public Usuario obtenerUsuarioporId(Long id);
	public Usuario actualizarUsuario(Usuario usuario);
	public void eliminarUsuario(Long id);
}