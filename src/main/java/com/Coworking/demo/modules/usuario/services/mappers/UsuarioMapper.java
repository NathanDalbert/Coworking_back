package com.Coworking.demo.modules.usuario.services.mappers;

import com.Coworking.demo.modules.usuario.DTO.UsuarioRequest;
import com.Coworking.demo.modules.usuario.DTO.UsuarioResponse;
import com.Coworking.demo.modules.usuario.domain.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {


    public Usuario toEntity(UsuarioRequest usuarioRequest) {
        return Usuario.newUsuario(
                usuarioRequest.nome(),
                usuarioRequest.email(),
                usuarioRequest.senha(),
                usuarioRequest.fotoPerfil(),
                usuarioRequest.integridade(),
                usuarioRequest.dataNascimento()
        );
    }

    public UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getFotoPerfil(),
                usuario.getIntegridade(),
                usuario.getDataNascimento()
        );
    }
}
