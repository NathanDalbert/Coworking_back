package com.Coworking.demo.modules.contatos.DTO;

import java.util.UUID;

public record ContatosResponse(UUID id,String telefone, UUID usuario) {
}
