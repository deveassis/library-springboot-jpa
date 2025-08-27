package io.github.cursodatajava.libraryapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "autor", schema="public")
@Getter // com essa anotation gera automaticamente os getters e setters na compilacao atraves do lombok
@Setter // com essa anotation gera automaticamente os getters e setters na compilacao atraves do lombok
public class Autor {

    @Id // informando que e um id
    @Column(name = "id") //mapeando o nome da coluna
    @GeneratedValue(strategy = GenerationType.UUID) // o JPA ira gerar o valor automaticamente
    private UUID id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "nacionalidade", nullable = false, length = 50)
    private String nacionalidade;

    @OneToMany(mappedBy = "autor") // Um autor para muitos livros, mapeado atraves da propriedade autor da classe Livro
    private List<Livro> livros; // isto nao e uma coluna


}
