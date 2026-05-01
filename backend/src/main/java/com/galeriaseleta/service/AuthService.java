package com.galeriaseleta.service;

import com.galeriaseleta.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Object login(String email, String senha) {
        throw new UnsupportedOperationException("Não implementado");
    }

    public Object registrar(Map<String, Object> dados) {
        throw new UnsupportedOperationException("Não implementado");
    }

    public void logout() {
        throw new UnsupportedOperationException("Não implementado");
    }

    public Object refreshToken(String refreshToken) {
        throw new UnsupportedOperationException("Não implementado");
    }

    public void esqueceuSenha(String email) {
        throw new UnsupportedOperationException("Não implementado");
    }

    public void redefinirSenha(String token, String novaSenha) {
        throw new UnsupportedOperationException("Não implementado");
    }
}
