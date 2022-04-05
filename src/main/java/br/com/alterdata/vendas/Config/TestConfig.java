package br.com.alterdata.vendas.Config;

import br.com.alterdata.vendas.enums.Categorias;
import br.com.alterdata.vendas.model.Produto;
import br.com.alterdata.vendas.repository.ProdutoRepository;
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

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public void run(String... args) throws Exception {
        Produto prod1 = new Produto(null, "Celular Iphone", "Lançamento 2022", "Iphone S13 Plus", new BigDecimal("4500"),Categorias.INFORMATICA);
        Produto prod2 = new Produto(null, "Guarda Roupa", "6 Portas com Espelho", "Guarda Roupa Paris G01", new BigDecimal("1299.99"),Categorias.MOVEIS);

        produtoRepository.saveAll(Arrays.asList(prod1, prod2));
    }
}