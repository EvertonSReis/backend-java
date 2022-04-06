package br.com.alterdata.vendas.controller;



import br.com.alterdata.vendas.dto.UsuarioLogindto;
import br.com.alterdata.vendas.dto.UsuarioUpdateRoledto;
import br.com.alterdata.vendas.model.Usuario;
import br.com.alterdata.vendas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
        Usuario obj = usuarioService.salvar(usuario);
        return ResponseEntity.ok().body(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable(name = "id") Long id,
                                          @RequestBody Usuario usuario){
        usuario.setId(id);
        Usuario updateProduto = usuarioService.update(usuario);
        return ResponseEntity.ok(updateProduto);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = usuarioService.listar();
        return ResponseEntity.ok().body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> listarPorId(@PathVariable Long id){
        Usuario usuario = usuarioService.listarPorId(id);
        return ResponseEntity.ok().body(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> delete(@PathVariable Long id){
        usuarioService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/role/{id}")
    public ResponseEntity<?> updateRole(@PathVariable(name = "id") Long id,
                                             @RequestBody @Valid UsuarioUpdateRoledto usuariodto){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setRole(usuariodto.getRole());

        usuarioService.updateRoleUsuario(usuario);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody @Valid UsuarioLogindto usuario){
        Usuario loggedUsuario = usuarioService.login(usuario.getEmail(), usuario.getSenha());
        return ResponseEntity.ok(loggedUsuario);
    }
}
