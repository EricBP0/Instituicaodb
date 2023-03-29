package br.com.puc.model.dao;

import br.com.puc.model.config.ConnectionFactory;
import br.com.puc.model.model.Area;
import br.com.puc.model.model.Curso;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CursoDAO implements ICursoDAO {

    public Curso create(Curso curso) {
        try {
            Connection connection = ConnectionFactory.getConnection();

            try {
                String query = "INSERT INTO Cursos(nome, sigla, area)VALUES (?,?,?)";
                PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                statement.setString(1, curso.getNome());
                statement.setString(2, curso.getSigla());
                statement.setString(3, curso.getArea().toString());

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

            return curso;
        } catch (SQLException var7){
            throw new RuntimeException(var7);
        }
    }

    public String findByName(String nome) {
        String query = "SELECT * FROM Cursos WHERE nome = ?";
        Curso curso;
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nome);
            statement.executeQuery();
            ResultSet rs = statement.executeQuery();
            rs.next();
            curso = new Curso(
                    rs.getLong("codigo"),
                    rs.getString("nome"),
                    rs.getString("sigla"),
                    Area.valueOf(rs.getString("area"))
            );
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return curso.getSigla();
    }

    public Curso update(Curso curso) {
        try(Connection connection = ConnectionFactory.getConnection()){
            String query = "UPDATE Cursos SET " + "nome = ?, sigla = ?, area = ?" +
                    "WHERE codigo = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, curso.getNome());
            statement.setString(2, curso.getSigla());
            statement.setString(3, curso.getArea().toString());
            statement.setLong(4, curso.getCodigo());
            statement.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return curso;
    }

    public void delete(Integer codigo) {
        try(Connection connection = ConnectionFactory.getConnection()){
            String query = "DELETE FROM Cursos WHERE codigo = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1,codigo);
            statement.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Curso> findAll() {
        String query = "SELECT * FROM Cursos";
        List<Curso> lista = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeQuery();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Curso curso = new Curso();
                curso.setCodigo(rs.getLong("codigo"));
                curso.setNome(rs.getString("nome"));
                curso.setSigla(rs.getString("sigla"));
                curso.setArea(Area.valueOf(rs.getString("area")));
                lista.add(curso);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }


    public Optional<Curso> findById(Integer codigo) {
        String query = "SELECT * FROM Cursos WHERE codigo = ?";
        Curso curso;
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1,codigo);
            statement.executeQuery();
            ResultSet rs = statement.executeQuery();
            rs.next();
            curso = new Curso(
                    rs.getLong("codigo"),
                    rs.getString("nome"),
                    rs.getString("sigla"),
                    Area.valueOf(rs.getString("area"))
            );
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(curso);
    }


    public List<Curso> findByArea(Area var1) {
        String query = "SELECT * FROM Cursos WHERE area = ?";
        List<Curso> lista = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, var1.getDescricao());
            statement.executeQuery();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Curso curso = new Curso();
                curso.setCodigo(rs.getLong("codigo"));
                curso.setNome(rs.getString("nome"));
                curso.setSigla(rs.getString("sigla"));
                curso.setArea(Area.valueOf(rs.getString("area")));
                lista.add(curso);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }


    public Optional<Curso> findBySigla(String sigla) {
        String query = "SELECT * FROM Cursos WHERE sigla = ?";
        Curso curso;
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,sigla);
            statement.executeQuery();
            ResultSet rs = statement.executeQuery();
            rs.next();
            curso = new Curso(
                    rs.getLong("codigo"),
                    rs.getString("nome"),
                    rs.getString("sigla"),
                    Area.valueOf(rs.getString("area"))
            );
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(curso);
    }
}

