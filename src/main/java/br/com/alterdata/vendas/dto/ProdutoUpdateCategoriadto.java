package br.com.alterdata.vendas.dto;

import br.com.alterdata.vendas.enums.Categorias;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter @Setter
public class ProdutoUpdateCategoriadto {

    @NotNull(message = "Categoria não pode estar vazio")
    private Categorias categoria;
}
