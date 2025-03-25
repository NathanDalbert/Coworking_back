package com.Coworking.demo.modules.usuario.localidades.domain;

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
    private UUID id;

    @Column(name = "nome_local")
    private String nomeLocal;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "localizacao")
    private String localizacao;

    @ManyToOne
    @JoinColumn(name = "id")
    private Usuario usuario;

    public Localidades(String nomeLocal, String descricao, String localizacao, Usuario usuario) {
        this.nomeLocal = nomeLocal;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.usuario = usuario;
    }

    public static Localidades newLocalidades(String nomeLocal, String descricao, String localizacao, Usuario usuario){
        return new Localidades(nomeLocal, descricao, localizacao, usuario);
    }
}
