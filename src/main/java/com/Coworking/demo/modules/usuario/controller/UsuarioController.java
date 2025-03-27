package com.Coworking.demo.modules.usuario.controller;

import com.Coworking.demo.modules.usuario.DTO.UsuarioRequest;
import com.Coworking.demo.modules.usuario.DTO.UsuarioResponse;
import com.Coworking.demo.modules.usuario.services.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @PostMapping("/criar")
    public UsuarioResponse criarUsuario(@RequestBody UsuarioRequest usuarioRequest) {
        return usuarioService.criarUsuario(usuarioRequest);
    }


    @GetMapping("/logado")
    public UsuarioResponse pegarUsuarioLogado(HttpServletRequest request) {
        return usuarioService.pegarUsuarioLogado(request);  // Passa a requisição para acessar o usuarioId
    }


    @PutMapping("/atualizar")
    public UsuarioResponse atualizarUsuario(@RequestBody UsuarioRequest usuarioRequest, HttpServletRequest request) {
        return usuarioService.atualizarUsuario(usuarioRequest, request);  // Passa a requisição para acessar o usuarioId
    }


    @DeleteMapping("/deletar")
    public void deleteUsuario(HttpServletRequest request) {
        usuarioService.deleteUsuario(request);  // Passa a requisição para acessar o usuarioId
    }



}
