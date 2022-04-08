package br.com.alterdata.vendas.service;

import br.com.alterdata.vendas.Util.HashUtil;
import br.com.alterdata.vendas.model.Usuario;
import br.com.alterdata.vendas.model.model.PageModel;
import br.com.alterdata.vendas.model.model.PageRequestModel;
import br.com.alterdata.vendas.repository.UsuarioRepository;
import br.com.alterdata.vendas.specification.UsuarioSpecification;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario){
        String hash = HashUtil.getSecureHash(usuario.getSenha());
        usuario.setSenha(hash);

        Usuario salvarUsuario = usuarioRepository.save(usuario);
        return salvarUsuario;
    }

    public Usuario update(Usuario usuario){
        String hash = HashUtil.getSecureHash(usuario.getSenha());
        usuario.setSenha(hash);

        Usuario updateUsuario = usuarioRepository.save(usuario);
        return updateUsuario;
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario listarPorId(Long id) throws NotFoundException {
        Optional<Usuario> resultado = usuarioRepository.findById(id);
        return resultado.orElseThrow(()-> new NotFoundException("Não existe usuario com o id " + id));
    }

    public void delete(Long id){
        usuarioRepository.deleteById(id);
    }

    public int updateRoleUsuario(Usuario usuario){
        return usuarioRepository.updateRole(usuario.getId(), usuario.getRole());
    }

    public Usuario login(String email, String senha){
        senha = HashUtil.getSecureHash(senha);

        Optional<Usuario> obj = usuarioRepository.login(email, senha);
        return obj.orElse(new Usuario());
    }

    public PageModel<Usuario> listAllOnLazyModel(PageRequestModel pr){
        Pageable pageable = pr.toSpringPageRequest();

        Specification<Usuario> specification = UsuarioSpecification.search(pr.getSearch());

        Page<Usuario> page = usuarioRepository.findAll(specification, pageable);

        PageModel<Usuario> pm = new PageModel<>((int) page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
        return pm;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> resultado = usuarioRepository.findByEmail(email);

        if(!resultado.isPresent()) throw new UsernameNotFoundException("Usuario inexistente");

        Usuario usuario = resultado.get();

        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_" + usuario.getRole().name()));

        User user = new User(usuario.getEmail(), usuario.getSenha(), authorities);
        return user;
    }
}
