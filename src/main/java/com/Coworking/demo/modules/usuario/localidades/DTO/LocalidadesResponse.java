package com.Coworking.demo.modules.usuario.localidades.DTO;

import java.util.UUID;

public record LocalidadesResponse (UUID id,  String nomeLocal, String descricao, String localizacao, UUID usuario){
}
