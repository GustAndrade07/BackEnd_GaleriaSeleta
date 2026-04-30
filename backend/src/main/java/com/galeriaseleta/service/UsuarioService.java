package com.galeriaseleta.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /** Valida duplicidade de e-mail e criptografa a senha antes de salvar. */
    public Object cadastrar(Map<String, Object> dados) {
        throw new UnsupportedOperationException("Não implementado");
    }

    /** Autentica comparando a senha informada com o hash armazenado via BCrypt. */
    public Object login(String email, String senha) {
        throw new UnsupportedOperationException("Não implementado");
    }

    public Object buscarPorId(Long id) {
        throw new UnsupportedOperationException("Não implementado");
    }

    public Object buscarPorEmail(String email) {
        throw new UnsupportedOperationException("Não implementado");
    }

    /** Atualiza dados do usuário. Se o campo senha estiver presente, recriptografa antes de salvar. */
    public Object atualizar(Long id, Map<String, Object> dados) {
        throw new UnsupportedOperationException("Não implementado");
    }

    public void deletar(Long id) {
        throw new UnsupportedOperationException("Não implementado");
    }

    public List<Object> listarEnderecos() {
        throw new UnsupportedOperationException("Não implementado");
    }

    public Object adicionarEndereco(Map<String, Object> dados) {
        throw new UnsupportedOperationException("Não implementado");
    }

    public void removerEndereco(Long enderecoId) {
        throw new UnsupportedOperationException("Não implementado");
    }
}
