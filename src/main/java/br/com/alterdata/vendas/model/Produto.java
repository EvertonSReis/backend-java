package br.com.alterdata.vendas.model;

import br.com.alterdata.vendas.enums.Categorias;
import com.sun.istack.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "produtos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) private Long id;

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
