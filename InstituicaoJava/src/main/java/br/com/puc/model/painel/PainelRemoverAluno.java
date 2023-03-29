package br.com.puc.model.painel;

import br.com.puc.model.config.ConnectionFactory;
import br.com.puc.model.dao.AlunoDAO;
import br.com.puc.model.model.Aluno;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class PainelRemoverAluno {
    private JLabel matricula;
    private JTextField entradaMatricula;
    private JButton remover;

    JPanel painelRemoverAluno;

    public PainelRemoverAluno() {
        Aluno aluno = new Aluno();

        remover.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {
                if(entradaMatricula.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Matricula não pode estar vazia!","Erro", JOptionPane.ERROR_MESSAGE);
                }else{
                    try (Connection connection = ConnectionFactory.getConnection()) {
                        AlunoDAO alunoDAO = new AlunoDAO();
                        alunoDAO.delete(Long.valueOf(entradaMatricula.getText()));
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null,"Alguma coisa de errado aconteceu!");
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }
}
