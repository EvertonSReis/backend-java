package br.com.alterdata.vendas.security;

import br.com.alterdata.vendas.model.Usuario;
import br.com.alterdata.vendas.repository.UsuarioRepository;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("accessManager")
public class AccessManager {

    @Autowired UsuarioRepository usuarioRepository;

    public boolean isUsuario(Long id) throws NotFoundException {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Usuario> resultado = usuarioRepository.findByEmail(email);

        if(!resultado.isPresent()) throw new NotFoundException("Não existe usuario com o email = " + email);

        Usuario usuario = resultado.get();

        return usuario.getId() == id;
    }
}
