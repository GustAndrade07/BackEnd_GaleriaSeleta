package com.galeriaseleta.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Gerencia autenticação e registro de usuários.
 *
 * Futuramente: adicionar rate limiting para prevenir ataques de força bruta.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    /**
     * Autentica o usuário com e-mail e senha.
     */
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody Map<String, Object> body) {
        return ResponseEntity.ok().build();
    }

    /**
     * Registra um novo usuário na plataforma.
     * Futuramente: verificar unicidade do e-mail antes de criar a conta.
     * Futuramente: enviar e-mail de verificação de conta após o cadastro.
     */
    @PostMapping("/register")
    public ResponseEntity<Void> registrar(@RequestBody Map<String, Object> body) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Encerra a sessão do usuário.
     */
    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.noContent().build();
    }

    /**
     * Gera um novo access token a partir de um refresh token válido.
     * Body esperado: { "refreshToken": "..." }.
     */
    @PostMapping("/refresh")
    public ResponseEntity<Void> refreshToken(@RequestBody Map<String, String> body) {
        return ResponseEntity.ok().build();
    }

    /**
     * Inicia o fluxo de recuperação de senha enviando um link por e-mail.
     * Body esperado: { "email": "..." }.
     */
    @PostMapping("/forgot-password")
    public ResponseEntity<Void> esqueceuSenha(@RequestBody Map<String, String> body) {
        return ResponseEntity.ok().build();
    }

    /**
     * Redefine a senha usando um token de recuperação temporário.
     * Body esperado: { "token": "...", "novaSenha": "..." }.
     */
    @PostMapping("/reset-password")
    public ResponseEntity<Void> redefinirSenha(@RequestBody Map<String, String> body) {
        return ResponseEntity.ok().build();
    }
}
