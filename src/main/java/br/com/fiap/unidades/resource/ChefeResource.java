package br.com.fiap.unidades.resource;

import br.com.fiap.unidades.entity.Chefe;
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
import java.util.List;

@RestController
@RequestMapping( value = "/chefe")
public class ChefeResource {


    @Autowired
    private ChefeRepository repo;
    @Autowired
    private PessoaRepository repoPessoa;
    @Autowired
    private UnidadeRepository repoUnidade;

    @Autowired
    private UsuarioRepository repoUsuario;
    @GetMapping
    public ResponseEntity<List<Chefe>> findAll() {
        return ResponseEntity.ok(repo.findAll());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Chefe> findById(@PathVariable Long id) {

        var chefe = repo.findById(id);

        if (chefe.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(chefe.get());
    }
    @Transactional
    @PostMapping
    public ResponseEntity<Chefe> save(@RequestBody Chefe chefe) {
        Chefe save = repo.save(chefe);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(save.getId())
                .toUri();
        return ResponseEntity.created(uri).body(save);
    }
}
