package br.com.alterdata.vendas.security;

import br.com.alterdata.vendas.constant.ConstantesSeguranca;
import br.com.alterdata.vendas.controller.exception.ApiError;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String jwt = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(jwt == null || !jwt.startsWith(ConstantesSeguranca.JWT_PROVIDER)){
            ApiError error = new ApiError(HttpStatus.UNAUTHORIZED.value(), ConstantesSeguranca.JWT_INVALID_MSG, new Date());
            PrintWriter writer = response.getWriter();

            ObjectMapper mapper = new ObjectMapper();
            String errorString = mapper.writeValueAsString(error);

            writer.write(errorString);

            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());

            return;
        }

        jwt = jwt.replace(ConstantesSeguranca.JWT_PROVIDER, "");

        try {
            Claims claims = new JwtManager().parseToken(jwt);
            String email = claims.getSubject();
            List<String> roles = (List<String>) claims.get(ConstantesSeguranca.JWT_ROLE_KEY);

            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            roles.forEach(role -> {
                grantedAuthorities.add(new SimpleGrantedAuthority(role));
            });

            Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, grantedAuthorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e){
            ApiError error = new ApiError(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), new Date());
            PrintWriter writer = response.getWriter();

            ObjectMapper mapper = new ObjectMapper();
            String errorString = mapper.writeValueAsString(error);

            writer.write(errorString);

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());

            return;
        }
        filterChain.doFilter(request, response);
    }
}
