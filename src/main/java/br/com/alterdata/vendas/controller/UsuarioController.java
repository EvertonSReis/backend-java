package br.com.alterdata.vendas.controller;



import br.com.alterdata.vendas.dto.UsuarioLoginResponsedto;
import br.com.alterdata.vendas.dto.UsuarioLogindto;
import br.com.alterdata.vendas.dto.UsuarioUpdateRoledto;
import br.com.alterdata.vendas.model.Usuario;
import br.com.alterdata.vendas.model.model.PageModel;
import br.com.alterdata.vendas.model.model.PageRequestModel;
import br.com.alterdata.vendas.security.AccessManager;
import br.com.alterdata.vendas.security.JwtManager;
import br.com.alterdata.vendas.service.UsuarioService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    @Autowired private UsuarioService usuarioService;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtManager jwtManager;
    @Autowired private AccessManager accessManager;

    @Secured({ "ROLE_ADMINISTRADOR"})
    @PostMapping
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
        Usuario obj = usuarioService.salvar(usuario);
        return ResponseEntity.ok().body(obj);
    }

    @PreAuthorize("@accessManager.isUsuario(#id)")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable(name = "id") Long id,
                                          @RequestBody Usuario usuario){
        usuario.setId(id);
        Usuario updateProduto = usuarioService.update(usuario);
        return ResponseEntity.ok(updateProduto);
    }

    @GetMapping
    public ResponseEntity<PageModel<Usuario>> listar(@RequestParam Map<String, String> params) {
        PageRequestModel pr = new PageRequestModel(params);
        PageModel<Usuario> pm = usuarioService.listAllOnLazyModel(pr);
        return ResponseEntity.ok(pm);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> listarPorId(@PathVariable Long id) throws NotFoundException {
        Usuario usuario = usuarioService.listarPorId(id);
        return ResponseEntity.ok().body(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> delete(@PathVariable Long id){
        usuarioService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Secured({ "ROLE_ADMINISTRADOR"})
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
    public ResponseEntity<UsuarioLoginResponsedto> login(@RequestBody @Valid UsuarioLogindto usurious){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(usurious.getEmail(), usurious.getSenha());
        Authentication auth = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(auth);


        User user = (User) auth.getPrincipal();

        String email = user.getUsername();
        List<String> roles = user.getAuthorities().stream().map(authority -> authority.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(jwtManager.criacaoToken(email, roles));
    }
}
