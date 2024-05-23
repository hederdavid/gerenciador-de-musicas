package com.heder.gerenciadorDeMusicas;

import com.heder.gerenciadorDeMusicas.principal.Principal;
import com.heder.gerenciadorDeMusicas.repository.ArtistaRepository;
import com.heder.gerenciadorDeMusicas.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GerenciadorDeMusicasApplication implements CommandLineRunner {

	@Autowired
	private ArtistaRepository artistRepository;
	@Autowired
	private MusicaRepository musicaRepository;

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorDeMusicasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(artistRepository, musicaRepository);
		principal.exibirMenu();
	}
}
