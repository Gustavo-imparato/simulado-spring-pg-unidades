package br.com.fiap.unidades.repository;

import br.com.fiap.unidades.entity.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {
    List<Unidade> findByNome(String nome);

    List<Unidade> findBySigla(String sigla);

    List<Unidade> findByMacro(String macro);
}
