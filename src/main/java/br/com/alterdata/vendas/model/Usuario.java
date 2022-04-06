package br.com.alterdata.vendas.model;

import br.com.alterdata.vendas.enums.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull private String nome;

    @NotNull private String email;

    @NotNull private String senha;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

}
