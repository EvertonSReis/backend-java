package br.com.alterdata.vendas;

import br.com.alterdata.vendas.enums.Categorias;
import br.com.alterdata.vendas.model.Produto;
import br.com.alterdata.vendas.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProdutoControllerTests {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Test
    void contextLoads() {}

    @Test
    void saveTest(){
        Produto produto = new Produto(null,"Sofa 3 lugares", "Lançamento 2022", "Vermelho", new BigDecimal("1599.99"),Categorias.MOVEIS);
        Produto createdProduto = produtoRepository.save(produto);

        assertThat(createdProduto.getId()).isEqualTo(5L);
        System.out.println(produto);
    }

    @Test
    void updateTest(){
        Produto produto = new Produto(5L,"Sofa 3 lugares", "Lançamento 2021", "Vermelho", new BigDecimal("1599.99"),Categorias.MOVEIS);
        Produto updateProduto = produtoRepository.save(produto);

        assertThat(updateProduto.getDescricao()).isEqualTo("Lançamento 2021");
        System.out.println(produto);
    }

    @Test
    void updateCategoriaTest(){
        int affectedRows = produtoRepository.updateCategoria(1L, Categorias.MOVEIS);
        assertThat(affectedRows).isEqualTo(1);
    }

    @Test
    public void getByIdTest(){
        Optional<Produto> result = produtoRepository.findById(1L);
        Produto produto = result.get();

        assertThat(produto.getId()).isEqualTo(1L);
    }

    @Test
    public void listTest(){
        List<Produto> produto = produtoRepository.findAll();

        assertThat(produto.size()).isEqualTo(3);
    }

}
