package com.Coworking.demo.modules.usuario.services.interfaces;

import com.Coworking.demo.modules.usuario.DTO.UsuarioRequest;
import com.Coworking.demo.modules.usuario.DTO.UsuarioResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.List;

public interface UsuarioServiceInterface {


    UsuarioResponse criarUsuario(@Valid UsuarioRequest usuarioRequest);


    UsuarioResponse pegarUsuarioLogado(HttpServletRequest request);


    UsuarioResponse atualizarUsuario(@Valid UsuarioRequest usuarioRequest, HttpServletRequest request);


    void deleteUsuario(HttpServletRequest request);


}
