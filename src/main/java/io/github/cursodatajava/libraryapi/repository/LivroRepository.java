package io.github.cursodatajava.libraryapi.repository;

import io.github.cursodatajava.libraryapi.model.Autor;
import io.github.cursodatajava.libraryapi.model.GeneroLivro;
import io.github.cursodatajava.libraryapi.model.Livro;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
/**
 * @see LivroRepositoryTest
 */

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    // Query Methods personalizados podem ser adicionados aqui
   List<Livro> findByAutor(Autor autor);

   List<Livro> findByTitulo(String titulo);

   List<Livro> findByIsbn(String isbn);

   List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

   List<Livro> findByPrecoBetween(BigDecimal precoInicial, BigDecimal precoFinal);

   List<Livro> findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);

   List<Livro> findByTituloLike(String titulo);

   // JPQL -> referencia as entidades (Classes) e as propriedades (atributos)
   @Query("select l from Livro as l order by l.titulo, l.preco ")
   List<Livro> listarTodosOrdenadoPorTituloePreco();

   /**
    * select a *
    * from livro l
    * join autor a on a.id = l.id_autor
    */
   @Query("select a from Livro l join l.autor a")
   List<Autor> listarAutoresDosLivros();

   // select distinct l.* from livro l
   @Query("select distinct l.titulo from Livro l")
   List<String> listarNomesDiferentesLivros();

   @Query("""
      select l.genero
      from Livro l
      join l.autor a
      where a.nacionalidade = 'Brasileiro' order by l.genero         
  """)
   List<String> listarGeneroAutoresBrasileiros();

   //NAMED PARAMETERS -> Parametros nomeados
   @Query("select l from Livro l where l.genero = :genero order by :paramOrdenacao")
   List<Livro> findByGenero(@Param("genero") GeneroLivro generoLivro,
                            @Param("paramOrdenacao") String nomePropriedade);

   //POSITIONAL PARAMETERS ->
   @Query("select l from Livro l where l.genero = ?1 order by ?2")
   List<Livro> findByGeneroPositionalParameters(@Param("genero") GeneroLivro generoLivro,
                            @Param("paramOrdenacao") String nomePropriedade);


   @Modifying
   @Transactional
   @Query("delete from Livro where genero = ?1")
   void deleteByGenero(GeneroLivro generoLivro);


   @Modifying
   @Transactional
   @Query("update Livro set dataPublicacao = ?1")
   void updateLivro(LocalDate novaData);
}
