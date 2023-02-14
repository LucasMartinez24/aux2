package ar.com.edu.unju.edm.servicio.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ar.com.edu.unju.edm.repositorio.UsuarioRepositorio;
import ar.com.edu.unju.edm.servicio.UsuarioServicio;
import ar.com.edu.unju.edm.usuario.Usuario;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

	@Autowired
	private UsuarioRepositorio repositorio;

	public List<Usuario> listarTodosLosUsuarios() {
		List<Usuario> auxiliar = new ArrayList<>();
		List<Usuario> auxiliar2 = new ArrayList<>();
		auxiliar=(List<Usuario>) repositorio.findAll();	
		for (int i = 0; i < auxiliar.size(); i++) {
			if(auxiliar.get(i).getEstado().equals(true)){
				auxiliar2.add(auxiliar.get(i));
			}
		}
		return auxiliar2;
	}

	@Override
	public void guardarUsuario(Usuario usuario) {
		String pw=usuario.getPassword();
		BCryptPasswordEncoder coder= new BCryptPasswordEncoder(4);
		usuario.setPassword(coder.encode(pw));
		usuario.setEstado(true);
		repositorio.save(usuario);
	}

	@Override
	public Usuario obtenerUsuarioporId(Long id) {
		return repositorio.findById(id).get();
	}

	@Override
	public Usuario actualizarUsuario(Usuario usuario) {
		return repositorio.save(usuario);
	}

	@Override
	public void eliminarUsuario(Long id) {
		Usuario auxiliar =new Usuario();
		auxiliar=obtenerUsuarioporId(id);
		auxiliar.setEstado(false);
		repositorio.save(auxiliar);
	}

}
