package io.github.cursodatajava.libraryapi.repository;

import io.github.cursodatajava.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("Maria");
        // Metodo construtor nativo para criar datas staticas
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


}
