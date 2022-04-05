package br.com.alterdata.vendas;

import br.com.alterdata.vendas.enums.Categorias;
import br.com.alterdata.vendas.model.Produto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class VendasApplicationTests {

    @Test
    void contextLoads() {}

    @Test
    void saveTest(){
        Produto produto = new Produto(1L,"Celular", "Lançamento 2022", "Samsung", new BigDecimal("1599.99"),Categorias.INFORMATICA);
    }


}
