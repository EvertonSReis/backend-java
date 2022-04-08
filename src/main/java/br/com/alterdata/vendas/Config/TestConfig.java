package br.com.alterdata.vendas.Config;

import br.com.alterdata.vendas.enums.Categorias;
import br.com.alterdata.vendas.enums.Role;
import br.com.alterdata.vendas.model.Produto;
import br.com.alterdata.vendas.model.Usuario;
import br.com.alterdata.vendas.repository.ProdutoRepository;
import br.com.alterdata.vendas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.boot.CommandLineRunner;


import java.math.BigDecimal;
import java.util.Arrays;

/* Classe para testes e popular banco de dados h2 */
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired private ProdutoRepository produtoRepository;

    @Autowired private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        Produto prod1 = new Produto(null, "Celular Iphone", "Lançamento 2022", "Iphone S13 Plus", new BigDecimal("4500"),Categorias.INFORMATICA);
        Produto prod2 = new Produto(null, "Guarda Roupa", "6 Portas com Espelho", "Guarda Roupa Paris G01", new BigDecimal("1299.99"),Categorias.MOVEIS);
        Produto prod3 = new Produto(null, "Notebook Samsung", "SSD 500GB", "Samsung Ebook", new BigDecimal("3850.99"),Categorias.INFORMATICA);

        produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3));

        Usuario user1 = new Usuario(null, "Alterdata Software Supervisor", "supervisor@alterdata.com.br", "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92", Role.ADMINISTRADOR);
        Usuario user2 = new Usuario(null, "Alterdata Software Geral", "usuario@alterdata.com.br", "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92", Role.GERAL);

        usuarioRepository.saveAll(Arrays.asList(user1, user2));
    }
}
