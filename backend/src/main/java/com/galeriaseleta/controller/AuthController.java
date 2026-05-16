package com.galeriaseleta.controller;

import com.galeriaseleta.dto.request.AuthLoginRequest;
import com.galeriaseleta.dto.request.AuthRegisterRequest;
import com.galeriaseleta.dto.request.EsqueceuSenhaRequest;
import com.galeriaseleta.dto.request.RedefinirSenhaRequest;
import com.galeriaseleta.dto.response.UsuarioResponse;
import com.galeriaseleta.model.Usuario;
import com.galeriaseleta.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /** Autentica o usuário e retorna o JWT. Body: { email, senha }. */
    @PostMapping("/login")
    public ResponseEntity<UsuarioResponse> login(@RequestBody AuthLoginRequest request) {
        Usuario usuario = authService.login(request.getEmail(), request.getSenha());
        String token = authService.generateToken(usuario);
        return ResponseEntity.ok(UsuarioResponse.from(usuario, token));
    }

    /** Registra um novo usuário e retorna o JWT. */
    @PostMapping("/register")
    public ResponseEntity<UsuarioResponse> registrar(@RequestBody AuthRegisterRequest request) {
        Usuario usuario = authService.registrar(request);
        String token = authService.generateToken(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioResponse.from(usuario, token));
    }

    /** Encerra a sessão do usuário. */
    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        authService.logout();
        return ResponseEntity.noContent().build();
    }

    /** Renova o token de acesso. Body: { refreshToken }. */
    @PostMapping("/refresh")
    public ResponseEntity<Object> refreshToken(@RequestBody java.util.Map<String, String> body) {
        return ResponseEntity.ok(authService.refreshToken(body.get("refreshToken")));
    }

    /** Inicia o fluxo de recuperação de senha. */
    @PostMapping("/forgot-password")
    public ResponseEntity<Void> esqueceuSenha(@RequestBody EsqueceuSenhaRequest request) {
        authService.esqueceuSenha(request.getEmail());
        return ResponseEntity.ok().build();
    }

    /** Redefine a senha com o token de recuperação. */
    @PostMapping("/reset-password")
    public ResponseEntity<Void> redefinirSenha(@RequestBody RedefinirSenhaRequest request) {
        authService.redefinirSenha(request.getToken(), request.getNovaSenha());
        return ResponseEntity.ok().build();
    }
}
