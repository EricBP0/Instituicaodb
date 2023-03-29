package br.com.puc.model.painel;

import br.com.puc.model.config.ConnectionFactory;
import br.com.puc.model.dao.AlunoDAO;
import br.com.puc.model.dao.CursoDAO;
import br.com.puc.model.model.Aluno;
import br.com.puc.model.model.Curso;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class PainelAterarAluno {
    private JButton alterar;
    private JRadioButton sexo_M;
    private JRadioButton sexo_F;
    private JComboBox listaCursos;
    private JCheckBox maiorDeIdade;
    private JTextField entradaNome;
    private JLabel sexo;
    private JLabel curso;
    private JLabel nome;
    JPanel painelAlterarAluno;
    private JLabel codigo;
    private JTextField entradaCodigo;

    public PainelAterarAluno() {
        Aluno aluno = new Aluno();
        CursoDAO cursoDAO = new CursoDAO();

        for (Curso c: cursoDAO.findAll()) {
            listaCursos.addItem(c.getNome());
        }

        alterar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {



                if (nome.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nome não pode estar vazio!", "Erro", JOptionPane.ERROR_MESSAGE);

                } else if (listaCursos.getSelectedItem().equals("<Selecione>")) {
                    JOptionPane.showMessageDialog(null, "Selecione um curso!", "Erro", JOptionPane.ERROR_MESSAGE);

                } else {
                    String checkIdade = "";
                    String sexo = "";

                    if (maiorDeIdade.isSelected()) {
                        checkIdade = "Maior de idade";
                        aluno.setMaioridade(maiorDeIdade.isSelected());
                    } else {
                        checkIdade = "Menor de idade";
                        aluno.setMaioridade(false);
                    }

                    if (sexo_M.isSelected()) sexo = "masculino";
                    else if (sexo_F.isSelected()) sexo = "feminino";
                    else sexo = "não informado";

                    aluno.setCursos(cursoDAO.findByName((String) listaCursos.getSelectedItem()));

                    System.err.println("Nome: " + entradaNome.getText() + "\n" + checkIdade + "\n" + "Sexo: " + sexo + "\n" + "Curso: " + aluno.getCursos());
                    JOptionPane.showMessageDialog(null, "Nome: " + entradaNome.getText() + "\n" + checkIdade + "\n" + "Sexo: " + sexo + "\n" + "Curso: " + aluno.getCursos());

                    aluno.setMatricula(Long.parseLong(entradaCodigo.getText()));
                    aluno.setNome(entradaNome.getText());
                    aluno.setSexo(sexo);

                    try (Connection connection = ConnectionFactory.getConnection()) {
                        AlunoDAO alunoDAO = new AlunoDAO();
                        alunoDAO.update(aluno);
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null,"Alguma coisa de errado aconteceu!");
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }
}
