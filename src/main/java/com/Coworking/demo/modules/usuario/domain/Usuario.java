package com.Coworking.demo.modules.usuario.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity( name = "usuario")
@Table( name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private UUID id;

    @Column(name = "nome")
   private String nome;

    @Column(name = "dataNascimento")
    private String dataNascimento;


    @Column(name = "email")
   private String email;
    @Column(name = "senha")
   private String senha;
    @Column(name = "fotoPerfil")
    private String fotoPerfil;
    @Column(name = "integridade")
    private Boolean integridade;

    private Usuario( String nome, String email, String senha, String fotoPerfil, Boolean integridade, String dataNascimento) {

        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.fotoPerfil = fotoPerfil;
        this.integridade = integridade;
        this.dataNascimento = dataNascimento;
    }

    public static  Usuario newUsuario( String nome, String email, String senha, String fotoPerfil, Boolean integridade, String dataNascimento) {
        return new Usuario (nome, email, senha, fotoPerfil, integridade, dataNascimento );
    }


}
