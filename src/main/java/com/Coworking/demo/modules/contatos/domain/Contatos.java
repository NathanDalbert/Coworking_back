package com.Coworking.demo.modules.contatos.domain;

import com.Coworking.demo.modules.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "contato")
@Table(name = "contato")
public class Contatos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idContato;

    @Column(name = "telefone")
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Contatos(String telefone) {
        this.telefone = telefone;
    }

    public static Contatos newContato(String telefone){
        return new Contatos(telefone);
    }
}
