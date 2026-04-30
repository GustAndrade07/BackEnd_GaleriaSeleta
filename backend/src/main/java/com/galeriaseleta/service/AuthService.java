package com.galeriaseleta.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

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
