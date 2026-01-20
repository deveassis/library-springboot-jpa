package io.github.cursodatajava.libraryapi.repository;

import io.github.cursodatajava.libraryapi.model.Autor;
import io.github.cursodatajava.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    // Query Methods personalizados podem ser adicionados aqui
   List<Livro> findByAutor(Autor autor);

   List<Livro> findByTitulo(String titulo);


   List<Livro> findByIsbn(String isbn);

   List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

   List<Livro> findByPrecoBetween(BigDecimal precoInicial, BigDecimal precoFinal);

   List<Livro> findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);

   List<Livro> findByTituloLike(String titulo);
}
