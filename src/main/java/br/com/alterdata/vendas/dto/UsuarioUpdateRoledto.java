package br.com.alterdata.vendas.dto;

import br.com.alterdata.vendas.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class UsuarioUpdateRoledto {

    @NotNull
    private Role role;
}
