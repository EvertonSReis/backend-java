package br.com.alterdata.vendas.service;

import br.com.alterdata.vendas.model.Usuario;
import br.com.alterdata.vendas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario){
        Usuario salvarUsuario = usuarioRepository.save(usuario);
        return salvarUsuario;
    }

    public Usuario update(Usuario usuario){
        Usuario updateUsuario = usuarioRepository.save(usuario);
        return updateUsuario;
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario listarPorId(Long id){
        Optional<Usuario> obj = usuarioRepository.findById(id);
        return obj.get();
    }

    public void delete(Long id){
        usuarioRepository.deleteById(id);
    }

    public int updateRoleUsuario(Usuario usuario){
        return usuarioRepository.updateRole(usuario.getId(), usuario.getRole());
    }

    public Usuario login(String email, String senha){
        Optional<Usuario> obj = usuarioRepository.login(email, senha);
        return obj.orElse(new Usuario());
    }
}
