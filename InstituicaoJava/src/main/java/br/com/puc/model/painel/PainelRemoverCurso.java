package br.com.puc.model.painel;

import br.com.puc.model.config.ConnectionFactory;
import br.com.puc.model.dao.AlunoDAO;
import br.com.puc.model.dao.CursoDAO;
import br.com.puc.model.model.Curso;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class PainelRemoverCurso {
    JPanel painelRemoverCurso;
    private JTextField entradaCodigo;
    private JButton remover;
    private JLabel codigo;

    public PainelRemoverCurso() {
        Curso curso = new Curso();
        remover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if(entradaCodigo.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Matricula não pode estar vazia!","Erro", JOptionPane.ERROR_MESSAGE);
                }else{
                    try (Connection connection = ConnectionFactory.getConnection()) {
                        CursoDAO cursoDAO = new CursoDAO();
                        cursoDAO.delete(Integer.valueOf(entradaCodigo.getText()));
                        JOptionPane.showMessageDialog(null, "Curso removido com sucesso!");
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null,"Alguma coisa de errado aconteceu!");
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }
}
