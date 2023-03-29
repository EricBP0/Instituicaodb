package br.com.puc.model.dao;

import br.com.puc.model.config.ConnectionFactory;
import br.com.puc.model.model.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlunoDAO implements IAlunoDAO {
    public AlunoDAO() {
    }

    public Aluno create(Aluno aluno) {
        try {
            Connection connection = ConnectionFactory.getConnection();

            try {
                String query = "INSERT INTO Alunos(nome, maioridade, siglaCurso, sexo)VALUES (?,?,?,?)";
                PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                statement.setString(1, aluno.getNome());
                statement.setBoolean(2, aluno.isMaioridade());
                statement.setString(3, aluno.getCursos());
                statement.setString(4, aluno.getSexo());

                statement.executeUpdate();
                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();
            } catch (Throwable var6) {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Throwable var5) {
                        var6.addSuppressed(var5);
                    }
                }

                throw var6;
            }

            connection.close();

            return aluno;
        } catch (SQLException var7) {
            throw new RuntimeException(var7);
        }
    }

    public Optional<Aluno> findById(Integer matricula) {
        String query = "SELECT * FROM alunos WHERE matricula = ?";
        Aluno aluno;
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1,matricula);
            statement.executeQuery();
            ResultSet rs = statement.executeQuery();
            rs.next();
            aluno = new Aluno(
                    rs.getLong("matricula"),
                    rs.getString("nome"),
                    rs.getBoolean("maioridade"),
                    rs.getString("siglaCurso"),
                    rs.getString("sexo")
            );
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(aluno);
    }

    public List<Aluno> findByCurso (String cursoSigla){
        String query = "SELECT * FROM alunos WHERE siglaCurso = ?";
        List<Aluno> lista = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, cursoSigla);
            statement.executeQuery();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setMatricula(rs.getLong("matricula"));
                aluno.setNome(rs.getString("nome"));
                aluno.setMaioridade(rs.getBoolean("maioridade"));
                aluno.setCursos(rs.getString("siglaCurso"));
                aluno.setSexo(rs.getString("sexo"));
                lista.add(aluno);
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List<Aluno> findAll () {
        String query = "SELECT * FROM alunos";
        List<Aluno> lista = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeQuery();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setMatricula(rs.getLong("matricula"));
                aluno.setNome(rs.getString("nome"));
                aluno.setMaioridade(rs.getBoolean("maioridade"));
                aluno.setCursos(rs.getString("siglaCurso"));
                aluno.setSexo(rs.getString("sexo"));
                lista.add(aluno);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public Aluno update(Aluno aluno){
        try(Connection connection = ConnectionFactory.getConnection()){
            String query = "UPDATE Alunos SET " + "nome = ?, maioridade = ?, siglaCurso = ?, sexo = ?" +
                    "WHERE matricula = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, aluno.getNome());
            statement.setBoolean(2, aluno.isMaioridade());
            statement.setString(3, aluno.getCursos());
            statement.setString(4, aluno.getSexo());
            statement.setLong(5, aluno.getMatricula());
            statement.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return aluno;
    }
    public void delete(Long matricula){
        try(Connection connection = ConnectionFactory.getConnection()){
            String query = "DELETE FROM Alunos WHERE matricula = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1,matricula);
            statement.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}

