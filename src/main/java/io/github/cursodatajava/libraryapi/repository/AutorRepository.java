package io.github.cursodatajava.libraryapi.repository;

import io.github.cursodatajava.libraryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

// Vai extender a classe Autor capturando o Id
public interface AutorRepository extends JpaRepository<Autor, UUID> {

}
