package br.com.alterdata.vendas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class UsuarioLoginResponsedto implements Serializable {

    public static final long serialVersionUID = 1L;

    private String token;
    private Long expireIn;
    private String tokenProvider;
}
