package io.github.cursodatajava.libraryapi;
import io.github.cursodatajava.libraryapi.model.Autor;
import io.github.cursodatajava.libraryapi.repository.AutorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.UUID;


@SpringBootApplication
public class Application {

	public Application() {
		super();
	}

	public static void main(String[] args) {
		var context = SpringApplication.run(Application.class, args);
	}



}
