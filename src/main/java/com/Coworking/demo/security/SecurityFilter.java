package com.Coworking.demo.security;

import com.Coworking.demo.modules.usuario.repository.UsuarioRepository;
import com.Coworking.demo.modules.usuario.domain.Usuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.recuperarToken(request);

        if (token != null) {
            String email = tokenService.validateToken(token);
            if (email != null && !email.isEmpty()) {
                Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);

                if (usuario != null) {

                    UserDetails userDetails = new User(usuario.getEmail(), usuario.getSenha(), new ArrayList<>()); // Adicionando authorities, se necessário
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);


                    request.setAttribute("usuarioId", usuario.getId());
                }
            }
        }


    }

    private String recuperarToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);  // Retorna o token removendo "Bearer "
        }
        return null;
    }
}
