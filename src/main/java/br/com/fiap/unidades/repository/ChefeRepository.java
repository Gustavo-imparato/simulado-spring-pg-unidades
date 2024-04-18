package br.com.fiap.unidades.repository;

import br.com.fiap.unidades.entity.Chefe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChefeRepository extends JpaRepository<Chefe, Long> {
    List<Chefe> findBySubstituto(String substituto);

    List<Chefe> findByUsuario(String usuario);

    List<Chefe> findByUnidade(String unidade);
}
