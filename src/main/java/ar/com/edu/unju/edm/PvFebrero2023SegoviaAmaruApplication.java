package ar.com.edu.unju.edm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ar.com.edu.unju.edm.repositorio.UsuarioRepositorio;


@SpringBootApplication
public class PvFebrero2023SegoviaAmaruApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PvFebrero2023SegoviaAmaruApplication.class, args);
	}

	@Autowired
	private UsuarioRepositorio repositorio;
	
	@Override
	public void run(String... args) throws Exception {
		/*
		Usuario usuario1 = new Usuario("Maru", "Felis", "amaruse123@gmail.com");
		repositorio.save(usuario1);
		Usuario usuario2 = new Usuario("Seba", "diaz", "sebita@gmail.com");
		repositorio.save(usuario2);
	*/
	}

}