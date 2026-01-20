package io.github.cursodatajava.libraryapi.repository;

import io.github.cursodatajava.libraryapi.model.Autor;
import io.github.cursodatajava.libraryapi.model.GeneroLivro;
import io.github.cursodatajava.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;


    @Test
    void salvarTest(){
        Autor autor = autorRepository.findById
                (UUID.fromString("2c1e4f29-e1da-4956-bd1d-217ac584ba03"))
                .orElse(null);
        Livro livro = new Livro();
        livro.setIsbn("9090-7070");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(2025, 9, 24));
        livro.setAutor(autor);
        livroRepository.save(livro);
    }

    @Test
    void salvarCascadeTeste(){
        Livro livro = new Livro();
        livro.setIsbn("8890-7070");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("AS TRANCAS DO REI CARECA");
        livro.setDataPublicacao(LocalDate.of(2026, 1, 15));

        Autor autor = new Autor();
        autor.setNome("Joao Gomes");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1997, 12, 10));

        autorRepository.save(autor);
        livro.setAutor(autor);
        livroRepository.save(livro);
    }

    @Test
    void atualizarAutorDoLivroTeste() {
        Livro livro = livroRepository.findById(UUID.fromString("63d57b8c-ad6d-424d-8f1c-dc40cf1af095")).orElse(null);
        Autor autor = autorRepository.findById(UUID.fromString("755ab1b4-3210-4140-bbcb-4323679b887a")).orElse(null);

        livro.setAutor(autor);

    }


    @Test
    void deletarLivro(){
        UUID livro = UUID.fromString("63d57b8c-ad6d-424d-8f1c-dc40cf1af095");
        livroRepository.deleteById(livro);
    }

    @Test
    @Transactional
    void buscarLivroTeste(){
        UUID id = UUID.fromString("df991249-26a4-4314-8a61-52e462659a65");
        Livro livro = livroRepository.findById(id).orElse(null);
        System.out.println(livro.getTitulo());
        System.out.println(livro.getAutor().getNome());
    }


    @Test
    void pesquisaLivroPorTituloTeste(){
        List<Livro> lista = livroRepository.findByTitulo("UFO");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisarPorISBN(){
        List<Livro> lista = livroRepository.findByIsbn("8890-7070");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisarPorTituloEPrecoTeste(){
        var titulo = "AS TRANCAS DO REI CARECA";
        var preco = BigDecimal.valueOf(100);

        List<Livro> listaNew = livroRepository.findByTituloAndPreco(titulo, preco);
        listaNew.forEach(System.out::println);
    }

    @Test
    void filtroPorPreco(){
        var precoInicial = BigDecimal.valueOf(124);
        var precoFinal = BigDecimal.valueOf(150);

        List<Livro> lista = livroRepository.findByPrecoBetween(precoInicial, precoFinal);
        lista.forEach(System.out::println);

    }

    @Test
    void filtroPorDataPublicacao(){
        var dataInicio = LocalDate.of(2025, 1, 1);
        var dataFim = LocalDate.of(2026, 12, 31);

        List<Livro> lista = livroRepository.findByDataPublicacaoBetween(dataInicio, dataFim);
        lista.forEach(System.out::println);
    }

    @Test
    void findByTituloLike(){
        var titulo = "%S TRANCAS%";
        List<Livro> lista = livroRepository.findByTituloLike(titulo);
        lista.forEach(System.out::println);
    }
}