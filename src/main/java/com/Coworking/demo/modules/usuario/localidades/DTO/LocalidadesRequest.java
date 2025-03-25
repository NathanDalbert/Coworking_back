package com.Coworking.demo.modules.usuario.localidades.DTO;

import java.util.UUID;

public record LocalidadesRequest (String nomeLocal, String descricao, String localizacao, UUID usuario){
}
