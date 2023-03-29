package br.com.puc.model;

import br.com.puc.model.painel.PainelCadastroAluno;
import br.com.puc.model.painel.PainelCadastroCurso;
import br.com.puc.model.painel.PainelPrincipal;

import javax.swing.*;

public class Principal extends PainelCadastroAluno {
    public Principal() {
    }
    // Teste para adicionar aluno no banco

   public static void main(String[] args) {
        JFrame quadro = new JFrame( "Gerencia de Alunos");
        quadro.setContentPane(new PainelPrincipal().painelPrincipal);
        quadro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        quadro.pack();
        quadro.setVisible(true);

    }
}
