package br.com.puc.model.painel;

import br.com.puc.model.dao.CursoDAO;
import br.com.puc.model.model.Area;
import br.com.puc.model.model.Curso;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelCadastroCurso {
    private JLabel nomeCurso;
    private JLabel siglaCurso;
    private JLabel areaCurso;
    private JButton enviar;
    private JComboBox escolhaArea;
    private JTextField entradaNome;
    private JTextField entradaSigla;
    public JPanel painelCursoCadastro;

    public PainelCadastroCurso() {
        Curso curso = new Curso();
        enviar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {
                if(nomeCurso.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Nome não pode estar vazio!", "Erro", JOptionPane.ERROR_MESSAGE);

                }else if(escolhaArea.getSelectedItem().equals("<Selecione>")){
                    JOptionPane.showMessageDialog(null, "Selecione um curso!", "Erro", JOptionPane.ERROR_MESSAGE);

                } else{
                    String textoArea = "";

                if (escolhaArea.getSelectedItem().equals("Exatas")) {
                    textoArea = "Exatas";
                    curso.setArea(Area.exatas);
                } else if (escolhaArea.getSelectedItem().equals("Humanas")) {
                    textoArea = "Humanas";
                    curso.setArea(Area.humanas);
                }else if (escolhaArea.getSelectedItem().equals("Biológicas")) {
                    textoArea = "Biológicas";
                    curso.setArea(Area.biologicas);
                }else if (escolhaArea.getSelectedItem().equals("Artes")) {
                    textoArea = "Artes";
                    curso.setArea(Area.artes);
                }else if (escolhaArea.getSelectedItem().equals("Outros")) {
                    textoArea = "Outros";
                    curso.setArea(Area.outros);

                }

                    System.err.println("Nome: " + entradaNome.getText() + "\n" +  "Sigla: " + entradaSigla.getText() +
                            "\n" + "Area: " + textoArea);
                    JOptionPane.showMessageDialog(null, "Nome: " + entradaNome.getText()
                            +"\n" + "Sigla: " + entradaSigla.getText() + "\n" + "Area: " + textoArea);

                    curso.setNome(entradaNome.getText());
                    curso.setSigla(entradaSigla.getText());

                    CursoDAO cursoDAO = new CursoDAO();
                    cursoDAO.create(curso);
                }
            }
        });
    }
}
