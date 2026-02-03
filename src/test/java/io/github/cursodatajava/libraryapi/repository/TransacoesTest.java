package io.github.cursodatajava.libraryapi.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransacoesTest {

    @Autowired
    AutorRepository autorRepository;


    /*
    * commit -> confirmar as alteracoes
    * transacao -> toda operacao realizada entre banco e servidor
    * rollback -> desfazer as alteracoes
     */
    @Test
    @Transactional
    void transacaoSimples(){
        // salvar um livro
        // salvar o autor
        // alugar o livro
        // enviar email pro locatario
        // notificar que o livro saiu da livraria
        // dentro disto estamos com uma transacao em aberto sendo tratada esstas atividades


    }
}
