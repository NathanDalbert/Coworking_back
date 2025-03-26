package com.Coworking.demo.modules.salas.DTO;

import com.Coworking.demo.modules.salas.domain.Salas.Disponibilidade;

import java.sql.Time;

public record SalasResponse(
        Integer id,
        String nomeSala,
        String descricao,
        Float tamanhoSala,
        Float precoPorHora,
        Time tempoInicioDisponivel,
        Time tempoFimDisponivel,
        Disponibilidade disponibilidade,
        Integer localidadeId,
        String nomeLocalidade) {
}