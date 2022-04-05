package br.com.alterdata.vendas.model;

import br.com.alterdata.vendas.enums.Categorias;
import com.sun.istack.NotNull;
import java.math.BigDecimal;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produtos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @NotNull private String nome;

    @NotNull private String descricao;

    @NotNull private String referencia;

    @NotNull
    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;


    @Column(name = "Categoria_Produto", length = 20, nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private Categorias categoria;

}
