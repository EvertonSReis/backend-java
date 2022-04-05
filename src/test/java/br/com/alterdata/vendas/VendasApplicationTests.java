package br.com.alterdata.vendas;

import br.com.alterdata.vendas.enums.Categorias;
import br.com.alterdata.vendas.model.Produto;
import br.com.alterdata.vendas.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VendasApplicationTests {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Test
    void contextLoads() {}

    @Test
    void saveTest(){
        Produto produto = new Produto(1L,"Celular", "Lançamento 2022", "Samsung", new BigDecimal("1599.99"),Categorias.INFORMATICA);
    }

    @Test
    void updateCategoriaTest(){
        int affectedRows = produtoRepository.updateCategoria(1L, Categorias.MOVEIS);
        assertThat(affectedRows).isEqualTo(1);
    }


}
