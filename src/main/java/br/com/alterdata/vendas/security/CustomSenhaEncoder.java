package br.com.alterdata.vendas.security;

import br.com.alterdata.vendas.Util.HashUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomSenhaEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        String hash = HashUtil.getSecureHash(charSequence.toString());
        return hash;
    }

    @Override
    public boolean matches(CharSequence charSequence, String encondedSenha) {
        String hash = HashUtil.getSecureHash(charSequence.toString());
        return hash.equals(encondedSenha);
    }
}
