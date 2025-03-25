package com.Coworking.demo.modules.localidades.DTO;

import java.util.UUID;

public record LocalidadesRequest (String nomeLocal, String descricao, String localizacao, UUID usuario){
}
