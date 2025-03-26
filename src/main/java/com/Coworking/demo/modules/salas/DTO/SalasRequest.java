package com.Coworking.demo.modules.salas.DTO;

import com.Coworking.demo.modules.salas.domain.Salas.Disponibilidade;

import java.sql.Time;

public record SalasRequest(
        Integer localidadeId,
        String nomeSala,
        String descricao,
        Float tamanhoSala,
        Float precoPorHora,
        Time tempoInicioDisponivel,
        Time tempoFimDisponivel,
        Disponibilidade disponibilidade) {
}
