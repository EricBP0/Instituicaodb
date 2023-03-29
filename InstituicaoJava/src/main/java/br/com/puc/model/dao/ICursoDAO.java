package br.com.puc.model.dao;

import br.com.puc.model.model.Area;
import br.com.puc.model.model.Curso;

import java.util.List;
import java.util.Optional;

public interface ICursoDAO {
    Curso create(Curso var1);

    Curso update(Curso var1);

    void delete(Integer var1);

    List<Curso> findAll();

    Optional<Curso> findById(Integer var1);

    List<Curso> findByArea(Area var1);

    Optional<Curso> findBySigla(String var1);
}
