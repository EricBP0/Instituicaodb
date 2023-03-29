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

public class PainelBuscarCurso {
    JPanel painelBuscarCurso;
    private JButton buscarTodos;
    private JLabel codigo;
    private JLabel sigla;
    private JLabel area;
    private JComboBox selecionarArea;
    private JButton buscarArea;
    private JButton buscarSigla;
    private JButton buscarCodigo;
    private JTextField entradaCodigo;
    private JTextField entradaSigla;

    public PainelBuscarCurso(){
        buscarTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CursoDAO cursoDAO = new CursoDAO();
                for (Curso c: cursoDAO.findAll()) {
                    System.out.println(c.getNome());
                    System.out.println(c.getArea());
                    System.out.println(c.getSigla());
                    System.out.println(c.getCodigo());
                }
            }
        });

        buscarArea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (selecionarArea.getSelectedItem().equals("<Selecione>")) {
                    JOptionPane.showMessageDialog(null, "Selecione um curso!", "Erro", JOptionPane.ERROR_MESSAGE);

                } else {
                    String textoArea = "";

                    if (selecionarArea.getSelectedItem().equals("Exatas")) {
                        textoArea = "exatas";

                    } else if (selecionarArea.getSelectedItem().equals("Humanas")) {
                        textoArea = "humanas";

                    } else if (selecionarArea.getSelectedItem().equals("Biológicas")) {
                        textoArea = "biologicas";

                    } else if (selecionarArea.getSelectedItem().equals("Artes")) {
                        textoArea = "artes";

                    } else if (selecionarArea.getSelectedItem().equals("Outros")) {
                        textoArea = "outros";
                    }
                    try (Connection connection = ConnectionFactory.getConnection()) {
                        CursoDAO cursoDAO = new CursoDAO();

                        for (Curso c: cursoDAO.findByArea(Area.valueOf(textoArea))) {
                            System.out.println(c.getNome());
                            System.out.println(c.getArea());
                            System.out.println(c.getSigla());
                            System.out.println(c.getCodigo());
                        }

                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null,"Alguma coisa de errado aconteceu!");
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        buscarCodigo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try (Connection connection = ConnectionFactory.getConnection()) {
                    CursoDAO cursoDAO = new CursoDAO();

                    Optional<Curso> curso = cursoDAO.findById(Integer.valueOf(entradaCodigo.getText()));
                    curso.ifPresent(c -> {
                        System.out.println(c.getCodigo());
                        System.out.println(c.getArea());
                        System.out.println(c.getNome());
                        System.out.println(c.getSigla());
                    });

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null,"Alguma coisa de errado aconteceu!");
                    throw new RuntimeException(e);
                }
            }
        });

        buscarSigla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try (Connection connection = ConnectionFactory.getConnection()) {
                    CursoDAO cursoDAO = new CursoDAO();
                    Optional<Curso> curso = cursoDAO.findBySigla(entradaSigla.getText());
                    curso.ifPresent(c ->{
                        System.out.println(c.getCodigo());
                        System.out.println(c.getNome());
                        System.out.println(c.getSigla());
                        System.out.println(c.getArea());
                    });
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null,"Alguma coisa de errado aconteceu!");
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
