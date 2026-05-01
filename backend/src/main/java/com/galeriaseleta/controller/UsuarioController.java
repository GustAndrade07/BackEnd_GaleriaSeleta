package com.galeriaseleta.controller;

import com.galeriaseleta.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /** Retorna o perfil do usuário autenticado. O ID será extraído do contexto de autenticação. */
    @GetMapping("/me")
    public ResponseEntity<Object> obterPerfil() {
        return ResponseEntity.ok(usuarioService.buscarPorId(0L));
    }

    /** Atualiza nome, sobrenome, e-mail e telefone do usuário autenticado. */
    @PutMapping("/me")
    public ResponseEntity<Object> atualizarPerfil(@RequestBody Map<String, Object> body) {
        return ResponseEntity.ok(usuarioService.atualizar(0L, body));
    }

    /** Altera a senha do usuário autenticado. Body: { senhaAtual, novaSenha }. */
    @PatchMapping("/me/senha")
    public ResponseEntity<Void> alterarSenha(@RequestBody Map<String, String> body) {
        usuarioService.atualizar(0L, new HashMap<>(body));
        return ResponseEntity.ok().build();
    }

    /** Remove a conta do usuário autenticado. */
    @DeleteMapping("/me")
    public ResponseEntity<Void> deletarConta() {
        usuarioService.deletar(0L);
        return ResponseEntity.noContent().build();
    }

    /** Lista os endereços de entrega do usuário autenticado. */
    @GetMapping("/me/enderecos")
    public ResponseEntity<Object> listarEnderecos() {
        return ResponseEntity.ok(usuarioService.listarEnderecos());
    }

    /** Adiciona um endereço de entrega ao perfil do usuário. */
    @PostMapping("/me/enderecos")
    public ResponseEntity<Object> adicionarEndereco(@RequestBody Map<String, Object> body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.adicionarEndereco(body));
    }

    /** Remove um endereço de entrega do perfil do usuário. */
    @DeleteMapping("/me/enderecos/{enderecoId}")
    public ResponseEntity<Void> removerEndereco(@PathVariable Long enderecoId) {
        usuarioService.removerEndereco(enderecoId);
        return ResponseEntity.noContent().build();
    }
}
