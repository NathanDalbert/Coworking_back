package com.Coworking.demo.modules.localidades.domain;

import com.Coworking.demo.modules.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity(name ="localidades")
@Table(name = "localidade")
public class Localidades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID idLocal;

    @Column(name = "nome_local")
    private String nomeLocal;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "localizacao")
    private String localizacao;

    @ManyToOne
    @JoinColumn(name = "locador_id")
    private Usuario usuario;

    public Localidades(String nomeLocal, String descricao, String localizacao) {
        this.nomeLocal = nomeLocal;
        this.descricao = descricao;
        this.localizacao = localizacao;
    }

    public static Localidades newLocalidades(String nomeLocal, String descricao, String localizacao){
        return new Localidades(nomeLocal, descricao, localizacao);
    }
}
