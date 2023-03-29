package br.com.puc.model.painel;

import br.com.puc.model.dao.AlunoDAO;
import br.com.puc.model.dao.CursoDAO;
import br.com.puc.model.model.Aluno;
import br.com.puc.model.model.Curso;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelCadastroAluno {
    public JPanel painelAlunoCadastro;
    private JButton enviar;
    private JLabel nome;
    private JTextField entradaNome;
    private JCheckBox maiorDeIdade;
    private JComboBox listaCursos;
    private JLabel idade;
    private JLabel sexo;
    private JRadioButton sexo_M;
    private JRadioButton sexo_F;
    private JLabel curso;
    private JTextField entradaMatricula;


    public PainelCadastroAluno() {
        Aluno aluno = new Aluno();

        CursoDAO cursoDAO = new CursoDAO();

        for (Curso c: cursoDAO.findAll()) {
            listaCursos.addItem(c.getNome());
        }
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(nome.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Nome não pode estar vazio!", "Erro", JOptionPane.ERROR_MESSAGE);

                }else if(listaCursos.getSelectedItem().equals("<Selecione>")){
                    JOptionPane.showMessageDialog(null, "Selecione um curso!", "Erro", JOptionPane.ERROR_MESSAGE);

                } else{
                    String checkIdade = "";
                    String textoCurso = "";
                    String sexo = "";

                    if(maiorDeIdade.isSelected()) {
                        checkIdade = "Maior de idade";
                        aluno.setMaioridade(maiorDeIdade.isSelected());
                    }
                    else {
                        checkIdade = "Menor de idade";
                        aluno.setMaioridade(false);
                    }



                    if(sexo_M.isSelected()) sexo = "masculino";
                    else if(sexo_F.isSelected()) sexo = "feminino";
                    else sexo = "não informado";

                    aluno.setCursos(cursoDAO.findByName((String) listaCursos.getSelectedItem()));

                    System.err.println("Nome: " + entradaNome.getText() + "\n" + checkIdade + "\n" + "Sexo: " + sexo + "\n" + "Curso: " + aluno.getCursos());
                    JOptionPane.showMessageDialog(null, "Nome: " + entradaNome.getText() + "\n" + checkIdade + "\n" + "Sexo: " + sexo + "\n" + "Curso: " + aluno.getCursos());

                    aluno.setNome(entradaNome.getText());
                    aluno.setSexo(sexo);

                    AlunoDAO alunoDAO = new AlunoDAO();
                    alunoDAO.create(aluno);
                }
            }
        });

    }
}
