package com.Coworking.demo.modules.salas.domain;

import com.Coworking.demo.modules.localidades.domain.Localidades;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "salas")
@Table(name = "salas")
public class Salas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSala;

    @ManyToOne
    @JoinColumn(name = "localidade_id")
    private Localidades localidade;

    @Column(name = "nome_sala")
    private String nomeSala;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "tamanho_sala")
    private Float tamanhoSala;

    @Column(name = "preco_por_hora")
    private Float precoPorHora;

    @Column(name = "tempo_inicio_disponivel")
    private Time tempoInicioDisponivel;

    @Column(name = "tempo_fim_disponivel")
    private Time tempoFimDisponivel;

    @Enumerated(EnumType.STRING)
    @Column(name = "disponibilidade")
    private Disponibilidade disponibilidade;

    public enum Disponibilidade {
        disponivel,
        ocupada
    }
}
