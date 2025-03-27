package com.Coworking.demo.modules.usuario.services;

import com.Coworking.demo.modules.usuario.services.interfaces.UsuarioServiceInterface;
import com.Coworking.demo.modules.usuario.services.mappers.UsuarioMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.Coworking.demo.modules.usuario.domain.Usuario;
import com.Coworking.demo.modules.usuario.DTO.UsuarioRequest;
import com.Coworking.demo.modules.usuario.DTO.UsuarioResponse;
import com.Coworking.demo.modules.usuario.repository.UsuarioRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UsuarioService implements UsuarioServiceInterface {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public UsuarioResponse criarUsuario(@Valid UsuarioRequest usuarioRequest) {
        Usuario usuario = usuarioMapper.toEntity(usuarioRequest);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return usuarioMapper.toResponse(savedUsuario);
    }

    @Override
    public UsuarioResponse pegarUsuarioLogado(HttpServletRequest request) {

        UUID usuarioId = (UUID) request.getAttribute("usuarioId");

        if (usuarioId == null) {
            throw new RuntimeException("Usuário não logado ou token inválido");
        }

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
        if (usuarioOptional.isPresent()) {
            return usuarioMapper.toResponse(usuarioOptional.get());
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }

    @Override
    public UsuarioResponse atualizarUsuario(@Valid UsuarioRequest usuarioRequest, HttpServletRequest request) {
        // Recupera o usuarioId da requisição
        UUID usuarioId = (UUID) request.getAttribute("usuarioId");

        if (usuarioId == null) {
            throw new RuntimeException("Usuário não logado ou token inválido");
        }

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            // Atualizando os dados do usuário
            usuario.setNome(usuarioRequest.nome());
            usuario.setEmail(usuarioRequest.email());
            usuario.setSenha(usuarioRequest.senha());
            usuario.setFotoPerfil(usuarioRequest.fotoPerfil());
            usuario.setIntegridade(usuarioRequest.integridade());
            usuario.setDataNascimento(usuarioRequest.dataNascimento());

            Usuario updatedUsuario = usuarioRepository.save(usuario);
            return usuarioMapper.toResponse(updatedUsuario);
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }

     @Override
    public void deleteUsuario(HttpServletRequest request) {
        // Recupera o usuarioId da requisição
        UUID usuarioId = (UUID) request.getAttribute("usuarioId");

        if (usuarioId == null) {
            throw new RuntimeException("Usuário não logado ou token inválido");
        }

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
        if (usuarioOptional.isPresent()) {
            usuarioRepository.delete(usuarioOptional.get());
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }
}