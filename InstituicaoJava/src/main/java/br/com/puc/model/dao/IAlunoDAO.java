package br.com.puc.model.dao;

import br.com.puc.model.model.Aluno;
import java.util.List;
import java.util.Optional;

public interface IAlunoDAO {
    Aluno create(Aluno var1);

    Aluno update(Aluno var1);

    void delete(Long var1);

    List<Aluno> findAll();

    Optional<Aluno> findById(Integer var1);

    List<Aluno> findByCurso(String var1);
}
