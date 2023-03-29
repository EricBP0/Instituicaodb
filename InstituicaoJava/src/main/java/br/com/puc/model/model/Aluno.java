package br.com.puc.model.model;

import java.util.Optional;

public class Aluno {
    private long matricula;
    private String nome;
    private boolean maioridade;
    private String cursoSigla;
    private String sexo;

    public Aluno(long matricula, String nome, boolean maioridade, String cursoSigla, String sexo) {
        this.matricula = matricula;
        this.nome = nome;
        this.maioridade = maioridade;
        this.cursoSigla = cursoSigla;
        this.sexo = sexo;
    }

    public Aluno() {
    }

    public Aluno(Optional<Aluno> byId) {
    }

    public long getMatricula() {
        return this.matricula;
    }

    public void setMatricula(long matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isMaioridade() {
        return this.maioridade;
    }

    public String getCursos() {
        return cursoSigla;
    }

    public void setCursos(String cursos) {
        this.cursoSigla = cursos;
    }
    public void setMaioridade(boolean maioridade) {
        this.maioridade = maioridade;
    }


    public String getSexo() {
        return this.sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}