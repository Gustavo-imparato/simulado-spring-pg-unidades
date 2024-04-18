package br.com.fiap.unidades.resource;

import br.com.fiap.unidades.entity.Chefe;
import br.com.fiap.unidades.entity.Unidade;
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
@RequestMapping( value = "/unidade")
public class UnidadeResource {




    @Autowired
    private UnidadeRepository repo;
    @Autowired
    private PessoaRepository repoPessoa;
    @Autowired
    private ChefeRepository repoChefe;

    @Autowired
    private UsuarioRepository repoUsuario;
    @GetMapping
    public ResponseEntity<List<Unidade>> findAll() {
        return ResponseEntity.ok(repo.findAll());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Unidade> findById(@PathVariable Long id) {

        var unidade = repo.findById(id);

        if (unidade.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(unidade.get());
    }

    @GetMapping(value = "/byNome/{nome}")
    public ResponseEntity<List<Unidade>> findByNome(@PathVariable String nome) {
        List<Unidade> unidades = repo.findByNome(nome);
        return unidades.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(unidades);
    }

    @GetMapping(value = "/bySigla/{sigla}")
    public ResponseEntity<List<Unidade>> findBySigla(@PathVariable String sigla) {
        List<Unidade> unidades = repo.findBySigla(sigla);
        return unidades.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(unidades);
    }

    @GetMapping(value = "/byMacro/{macro}")
    public ResponseEntity<List<Unidade>> findByMacro(@PathVariable String macro) {
        List<Unidade> unidades = repo.findByMacro(macro);
        return unidades.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(unidades);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Unidade> save(@RequestBody Unidade unidade) {
        Unidade save = repo.save( unidade);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(save.getId())
                .toUri();
        return ResponseEntity.created(uri).body(save);
    }
}
