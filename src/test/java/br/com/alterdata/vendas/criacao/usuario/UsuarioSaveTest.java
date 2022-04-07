package br.com.alterdata.vendas.criacao.usuario;

import br.com.alterdata.vendas.enums.Role;
import br.com.alterdata.vendas.model.Usuario;
import br.com.alterdata.vendas.repository.UsuarioRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UsuarioSaveTest {

    @Autowired UsuarioRepository usuarioRepository;

    @Test
    public void saveTest(){
        Usuario usuario = new Usuario(4L, "Everton Reis", "everton@gmail.com", "123456", Role.ADMINISTRADOR);
    }

}
