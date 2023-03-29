package br.com.puc.model.painel;

import br.com.puc.model.config.ConnectionFactory;
import br.com.puc.model.dao.AlunoDAO;
import br.com.puc.model.dao.CursoDAO;
import br.com.puc.model.model.Aluno;
import br.com.puc.model.model.Area;
import br.com.puc.model.model.Curso;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class PainelBuscarAluno {
    private JButton buscarTodos;
    private JButton buscarID;
    private JButton buscarCurso;
    private JLabel entradaSigla;
    private JComboBox selecionarCurso;
    private JLabel matricula;
    private JTextField entradaMatricula;
    JPanel painelBuscarAluno;

    public PainelBuscarAluno(){
        Aluno aluno = new Aluno();
        CursoDAO cursoDAO = new CursoDAO();

        for (Curso c: cursoDAO.findAll()) {
            selecionarCurso.addItem(c.getNome());
        }

       buscarTodos.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent actionEvent) {
               AlunoDAO alunoDAO = new AlunoDAO();
               for (Aluno a: alunoDAO.findAll()) {
                   System.out.println(a.getNome());
                   System.out.println(a.getCursos());
                   System.out.println(a.getSexo());
                   System.out.println(a.getMatricula());
               }
           }
       });

       buscarCurso.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent actionEvent) {

               String siglaCurso = cursoDAO.findByName((String) selecionarCurso.getSelectedItem());

               try (Connection connection = ConnectionFactory.getConnection()) {

                   AlunoDAO alunoDAO = new AlunoDAO();
                   for (Aluno a: alunoDAO.findByCurso(siglaCurso)) {
                       System.out.println(a.getNome());
                       System.out.println(a.getCursos());
                       System.out.println(a.getSexo());
                       System.out.println(a.getMatricula());
                   }
               } catch (SQLException e) {
                   JOptionPane.showMessageDialog(null,"Alguma coisa de errado aconteceu!");
                   throw new RuntimeException(e);
               }
           }
       });

       buscarID.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent actionEvent) {
               try (Connection connection = ConnectionFactory.getConnection()) {

                   AlunoDAO alunoDAO = new AlunoDAO();
                   Optional<Aluno> aluno  = alunoDAO.findById(Integer.valueOf(entradaMatricula.getText()));
                   aluno.ifPresent(a -> {
                       System.out.println("Matricula: " + a.getMatricula());
                       System.out.println("Nome: " + a.getNome());
                       System.out.println(a.isMaioridade()? " - Adulto":" -Adolescente");
                       System.out.println("Curso: " +  a.getCursos());
                       System.out.println("Sexo: " + a.getSexo());
                   });

               } catch (SQLException e) {
                   JOptionPane.showMessageDialog(null,"Alguma coisa de errado aconteceu!");
                   throw new RuntimeException(e);
               }
           }
       });

    }
}
