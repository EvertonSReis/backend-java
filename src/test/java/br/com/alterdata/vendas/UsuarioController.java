package br.com.alterdata.vendas;

import br.com.alterdata.vendas.enums.Role;
import br.com.alterdata.vendas.model.Produto;
import br.com.alterdata.vendas.model.Usuario;
import br.com.alterdata.vendas.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UsuarioController {

    @Autowired UsuarioRepository usuarioRepository;

    @Test
    void inserirNovoUsuario(){
        Usuario usuario = new Usuario(null, "Usuario Teste", "teste@alterdata.com", "123456", Role.ADMINISTRADOR);
        Usuario createdUsuario = usuarioRepository.save(usuario);

        assertThat(createdUsuario.getId()).isEqualTo(5L);
        System.out.println(usuario);
    }

    @Test
    void atualizarUsuarioExistente(){
        Usuario usuario = new Usuario(5L, "Alterdata Teste", "teste@alterdata.com", "123456", Role.ADMINISTRADOR);
        Usuario updateUsuario = usuarioRepository.save(usuario);

        assertThat(updateUsuario.getNome()).isEqualTo("Alterdata Teste");
        System.out.println(usuario);
    }

    @Test
    public void listTest(){
        List<Usuario> usuarios = usuarioRepository.findAll();

        assertThat(usuarios.size()).isEqualTo(1);
    }

    @Test
    public void getByIdTest(){
        Optional<Usuario> result = usuarioRepository.findById(4L);
        Usuario usuario = result.get();

        assertThat(usuario.getId()).isEqualTo(4L);
        System.out.println(result);
    }

    @Test
    public void loginTest(){
        Optional<Usuario> result = usuarioRepository.login("alterdata@gmail.com", "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92");
        Usuario logUsuario = result.get();

        assertThat(logUsuario.getId()).isEqualTo(4L);
    }

    @Test
    public void updateRoleTest(){
        int affectedRows = usuarioRepository.updateRole(4L,Role.GERAL);

        assertThat(affectedRows).isEqualTo(1);
    }
}
