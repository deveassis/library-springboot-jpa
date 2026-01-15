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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setDataNascimento(LocalDate.of(1999, 12, 10));
        autor.setNacionalidade("Brasileira");
        var autorSalvo = repository.save(autor);
        System.out.println("Autor Salvo" + autorSalvo);
    }


    @Test
    public void atualizarTest(){
        var id = UUID.fromString("49a78103-f6cb-4317-8cc1-e3a81b0bbc81");
        Optional<Autor> possivelAutor = repository.findById(id);

        if(possivelAutor.isPresent()){
            Autor autorEncontrado = possivelAutor.get();
            System.out.println(autorEncontrado);
            autorEncontrado.setDataNascimento(LocalDate.of(2001, 1, 30));
            repository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTest(){
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTeste(){
        System.out.println("Contagem de Autores: " + repository.count());
    }

    @Test
    public void deletePorIdTeste(){
        var id = UUID.fromString("49a78103-f6cb-4317-8cc1-e3a81b0bbc81");
        Optional<Autor> possivelAutor = repository.findById(id);
        if(possivelAutor.isPresent()){
            Autor autorEncontrado = possivelAutor.get();
            System.out.println(autorEncontrado);
            repository.deleteById(id);
        }
    }



    @Test
    public void deletePorObjetoTeste(){
        var id = UUID.fromString("5d9fadb0-fffc-49f0-bc04-c2c014b57f6c");
        var objeto = repository.findById(id).get();
        repository.delete(objeto);
    }

    @Test
    void salvarAutorComLivrosTest() {

        Autor autor = new Autor();
        autor.setNome("Antonio");
        autor.setDataNascimento(LocalDate.of(1997, 12, 10));
        autor.setNacionalidade("Brasileiro");

        Livro livro = new Livro();
        livro.setIsbn("1234-5678");
        livro.setTitulo("O TEYUN");
        livro.setPreco(new BigDecimal("10.40"));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setDataPublicacao(LocalDate.of(2026, 1, 15));
        livro.setAutor(autor); // 🔥 lado dono

        Livro livro2 = new Livro();
        livro2.setIsbn("9876-5432");
        livro2.setTitulo("A CASA DA SUA TIA");
        livro2.setGenero(GeneroLivro.FANTASIA);
        livro2.setPreco(new BigDecimal("20.50"));
        livro2.setDataPublicacao(LocalDate.of(2025, 5, 20));
        livro2.setAutor(autor); // 🔥 lado dono

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor); // ✅ salva tudo em cascata
    }

    @Test
    @Transactional
    void listarLivros() {
        var id = UUID.fromString("2efc25e8-6ca5-45f7-9923-78a8528b08a0");
        var autor = repository.findById(id).get();

        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);
        autor.getLivros().forEach(System.out::println);

    }



}
