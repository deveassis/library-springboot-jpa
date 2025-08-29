package io.github.cursodatajava.libraryapi.repository;

import io.github.cursodatajava.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
}
