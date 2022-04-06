package br.com.alterdata.vendas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioLogindto {

    @Email(message = "Invalid email address!")
    private String email;

    @NotBlank
    private String senha;
}
