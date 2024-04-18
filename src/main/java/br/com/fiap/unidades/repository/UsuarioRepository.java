package br.com.fiap.unidades.repository;

import br.com.fiap.unidades.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsernameIgnoreCase(String name);

    List<Usuario> findByPessoa_Email(String email);

    List<Usuario> findByPessoa_Nascimento(Date nascimento);

    List<Usuario> findByPessoa_Tipo(String tipo);

    List<Usuario> findByPessoa_Sobrenome(String sobrenome);

    List<Usuario> findByPessoa_Username(String username);

    List<Usuario> findByPessoa_Nome(String nome);
}
