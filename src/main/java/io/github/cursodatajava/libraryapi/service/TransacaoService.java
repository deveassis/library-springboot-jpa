package io.github.cursodatajava.libraryapi.service;

import io.github.cursodatajava.libraryapi.model.Autor;
import io.github.cursodatajava.libraryapi.model.GeneroLivro;
import io.github.cursodatajava.libraryapi.model.Livro;
import io.github.cursodatajava.libraryapi.repository.AutorRepository;
import io.github.cursodatajava.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TransacaoService {
    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void executar(){
        Autor autor = new Autor();
        autor.setNome("José");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1997, 12, 10));
        autorRepository.save(autor);

        if(autor.getNome().equals("José")){
            throw new RuntimeException("Rollback!");
        }

        Livro livro = new Livro();
        livro.setIsbn("8890-7070");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("PROGRAMACAO COM JAVA");
        livro.setDataPublicacao(LocalDate.of(2026, 1, 15));
        livro.setAutor(autor);
        livroRepository.save(livro);
    }
}
