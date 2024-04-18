package br.com.fiap.unidades.resource;

import br.com.fiap.unidades.entity.Unidade;
import br.com.fiap.unidades.entity.Usuario;
import br.com.fiap.unidades.repository.ChefeRepository;
import br.com.fiap.unidades.repository.PessoaRepository;
import br.com.fiap.unidades.repository.UnidadeRepository;
import br.com.fiap.unidades.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(name = "/usuario")
public class UsuarioResorce {
    @Autowired
    private UsuarioRepository repo;
    @Autowired
    private PessoaRepository repoPessoa;
    @Autowired
    private ChefeRepository repoChefe;

    @Autowired
    private UnidadeRepository repoUnidade;
    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        return ResponseEntity.ok(repo.findAll());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {

        var usuario = repo.findById(id);

        if (usuario.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(usuario.get());
    }

    @GetMapping(value = "/byUsername/{username}")
    public ResponseEntity<List<Usuario>> findByUsername(@PathVariable String username) {
        List<Usuario> chefes = repo.findByPessoa_Username(username);
        return chefes.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(chefes);
    }

    @GetMapping(value = "/byNome/{nome}")
    public ResponseEntity<List<Usuario>> findByNome(@PathVariable String nome) {
        List<Usuario> chefes = repo.findByPessoa_Nome(nome);
        return chefes.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(chefes);
    }

    @GetMapping(value = "/bySobrenome/{sobrenome}")
    public ResponseEntity<List<Usuario>> findBySobrenome(@PathVariable String sobrenome) {
        List<Usuario> chefes = repo.findByPessoa_Sobrenome(sobrenome);
        return chefes.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(chefes);
    }

    @GetMapping(value = "/byNascimento/{nascimento}")
    public ResponseEntity<List<Usuario>> findByNascimento(@PathVariable Date nascimento) {
        List<Usuario> chefes = repo.findByPessoa_Nascimento(nascimento);
        return chefes.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(chefes);
    }

    @GetMapping(value = "/byTipo/{tipo}")
    public ResponseEntity<List<Usuario>> findByTipo(@PathVariable String tipo) {
        List<Usuario> chefes = repo.findByPessoa_Tipo(tipo);
        return chefes.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(chefes);
    }

    @GetMapping(value = "/byEmail/{email}")
    public ResponseEntity<List<Usuario>> findByEmail(@PathVariable String email) {
        List<Usuario> chefes = repo.findByPessoa_Email(email);
        return chefes.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(chefes);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
        Usuario save = repo.save( usuario);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(save.getId())
                .toUri();
        return ResponseEntity.created(uri).body(save);
    }
}

