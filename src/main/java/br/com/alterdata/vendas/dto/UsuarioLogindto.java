package br.com.alterdata.vendas.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class UsuarioLogindto {

    @Email(message = "Invalid email address!")
    private String email;

    @NotBlank
    private String senha;
}
