package com.Coworking.demo.modules.usuario.DTO;

import java.util.UUID;

public record UsuarioResponse(UUID id, String nome, String email, String fotoPerfil, Boolean integridade) {
}
