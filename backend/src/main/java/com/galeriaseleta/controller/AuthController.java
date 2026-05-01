package com.galeriaseleta.controller;

import com.galeriaseleta.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /** Autentica o usuário com e-mail e senha. */
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Map<String, Object> body) {
        String email = (String) body.get("email");
        String senha = (String) body.get("senha");
        return ResponseEntity.ok(authService.login(email, senha));
    }

    /** Registra um novo usuário. */
    @PostMapping("/register")
    public ResponseEntity<Object> registrar(@RequestBody Map<String, Object> body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registrar(body));
    }

    /** Encerra a sessão do usuário. */
    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        authService.logout();
        return ResponseEntity.noContent().build();
    }

    /** Renova o token de acesso. Body: { refreshToken }. */
    @PostMapping("/refresh")
    public ResponseEntity<Object> refreshToken(@RequestBody Map<String, String> body) {
        return ResponseEntity.ok(authService.refreshToken(body.get("refreshToken")));
    }

    /** Inicia o fluxo de recuperação de senha. Body: { email }. */
    @PostMapping("/forgot-password")
    public ResponseEntity<Void> esqueceuSenha(@RequestBody Map<String, String> body) {
        authService.esqueceuSenha(body.get("email"));
        return ResponseEntity.ok().build();
    }

    /** Redefine a senha com o token de recuperação. Body: { token, novaSenha }. */
    @PostMapping("/reset-password")
    public ResponseEntity<Void> redefinirSenha(@RequestBody Map<String, String> body) {
        authService.redefinirSenha(body.get("token"), body.get("novaSenha"));
        return ResponseEntity.ok().build();
    }
}
