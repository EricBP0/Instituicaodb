package br.com.puc.model.painel;

import br.com.puc.model.config.ConnectionFactory;
import br.com.puc.model.dao.CursoDAO;
import br.com.puc.model.model.Area;
import br.com.puc.model.model.Curso;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class PainelAlterarCurso {
    JPanel painelAlterarCurso;
    private JButton alterar;
    private JTextField entradaNome;
    private JTextField entradaSigla;
    private JComboBox area;
    private JLabel nomeCurso;
    private JLabel siglaCurso;
    private JLabel areaCurso;
    private JLabel codigo;
    private JTextField entradaCodigo;

    public PainelAlterarCurso() {
        Curso curso = new Curso();
        alterar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {
                if(nomeCurso.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Nome não pode estar vazio!", "Erro", JOptionPane.ERROR_MESSAGE);

                }else if(area.getSelectedItem().equals("<Selecione>")){
                    JOptionPane.showMessageDialog(null, "Selecione um curso!", "Erro", JOptionPane.ERROR_MESSAGE);

                } else{
                    String textoArea = "";

                    if (area.getSelectedItem().equals("Exatas")) {
                        textoArea = "Exatas";
                        curso.setArea(Area.exatas);
                    } else if (area.getSelectedItem().equals("Humanas")) {
                        textoArea = "Humanas";
                        curso.setArea(Area.humanas);
                    }else if (area.getSelectedItem().equals("Biológicas")) {
                        textoArea = "Biológicas";
                        curso.setArea(Area.biologicas);
                    }else if (area.getSelectedItem().equals("Artes")) {
                        textoArea = "Artes";
                        curso.setArea(Area.artes);
                    }else if (area.getSelectedItem().equals("Outros")) {
                        textoArea = "Outros";
                        curso.setArea(Area.outros);

                    }

                    System.err.println("Nome: " + entradaNome.getText() + "\n" +  "Sigla: " + entradaSigla.getText() +
                            "\n" + "Area: " + textoArea);
                    JOptionPane.showMessageDialog(null, "Nome: " + entradaNome.getText()
                            +"\n" + "Sigla: " + entradaSigla.getText() + "\n" + "Area: " + textoArea);

                    curso.setCodigo(Long.valueOf(entradaCodigo.getText()));
                    curso.setNome(entradaNome.getText());
                    curso.setSigla(entradaSigla.getText());

                    try (Connection connection = ConnectionFactory.getConnection()) {
                        CursoDAO cursoDAO = new CursoDAO();
                        cursoDAO.update(curso);
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Alguma coisa de errado aconteceu!");
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }
}
