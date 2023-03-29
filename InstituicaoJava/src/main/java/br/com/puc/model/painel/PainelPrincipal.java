package br.com.puc.model.painel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelPrincipal {
    private JButton cadastrarAluno;
    private JButton cadastrarCurso;
    private JButton alterarAluno;
    private JButton removerAluno;
    private JButton buscarCursoButton;
    private JButton buscarAlunoButton;
    private JButton alterarCurso;
    private JButton removerCurso;
    public JPanel painelPrincipal;

    public PainelPrincipal(){
        alterarAluno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame quadro = new JFrame( "Alterar Aluno");
                quadro.setContentPane(new PainelAterarAluno().painelAlterarAluno);
                quadro.pack();
                quadro.setVisible(true);
            }
        });
        removerAluno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame quadro = new JFrame("Remover Aluno");
                quadro.setContentPane(new PainelRemoverAluno().painelRemoverAluno);
                quadro.pack();
                quadro.setVisible(true);
            }
        });

        alterarCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame quadro = new JFrame("Alterar Aluno");
                quadro.setContentPane(new PainelAlterarCurso().painelAlterarCurso);
                quadro.pack();
                quadro.setVisible(true);
            }
        });
        removerCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame quadro = new JFrame("Remover Curso");
                quadro.setContentPane(new PainelRemoverCurso().painelRemoverCurso);
                quadro.pack();
                quadro.setVisible(true);
            }
        });

        cadastrarAluno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame quadro = new JFrame( "Cadastro Aluno");
                quadro.setContentPane(new PainelCadastroAluno().painelAlunoCadastro);
                quadro.pack();
                quadro.setVisible(true);
            }
        });

        cadastrarCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame quadro = new JFrame( "Cadastro Curso");
                quadro.setContentPane(new PainelCadastroCurso().painelCursoCadastro);
                quadro.pack();
                quadro.setVisible(true);
            }
        });

        buscarAlunoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame quadro = new JFrame( "Buscar Aluno");
                quadro.setContentPane(new PainelBuscarAluno().painelBuscarAluno);
                quadro.pack();
                quadro.setVisible(true);
            }
        });

        buscarCursoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame quadro = new JFrame( "Bucar Curso");
                quadro.setContentPane(new PainelBuscarCurso().painelBuscarCurso);
                quadro.pack();
                quadro.setVisible(true);
            }
        });
    }
}
