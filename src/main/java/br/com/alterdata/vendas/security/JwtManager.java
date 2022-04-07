package br.com.alterdata.vendas.security;

import br.com.alterdata.vendas.constant.ConstantesSeguranca;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

@Component
public class JwtManager {

    public String criacaoToken(String email, List<String> roles){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, ConstantesSeguranca.JWT_EXP_DAYS);
        String jwt = Jwts.builder()
                .setSubject(email)
                .setExpiration(calendar.getTime())
                .claim(ConstantesSeguranca.JWT_ROLE_KEY, roles)
                .signWith(SignatureAlgorithm.HS512, ConstantesSeguranca.API_KEY.getBytes())
                .compact();

        return jwt;
    }
}
