package com.Coworking.demo.modules.usuario.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "usuario")
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private String dataNascimento;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "foto_perfil")
    private String fotoPerfil;

    @Column(name = "integridade", nullable = false)
    private Boolean integridade;


    public Usuario(String nome, String email, String senha, String fotoPerfil, Boolean integridade, String dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.fotoPerfil = fotoPerfil;
        this.integridade = integridade;
        this.dataNascimento = dataNascimento;
    }


    public static Usuario newUsuario(String nome, String email, String senha, String fotoPerfil, Boolean integridade, String dataNascimento) {
        return new Usuario(nome, email, senha, fotoPerfil, integridade, dataNascimento);
    }
}
