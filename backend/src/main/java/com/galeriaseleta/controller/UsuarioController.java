package com.galeriaseleta.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Gerencia o perfil e dados do usuário autenticado.
 *
 * Futuramente: injetar UsuarioService para delegar a lógica de negócio.
 * Futuramente: criar UsuarioAdminController em /api/admin/usuarios para operações administrativas.
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    /**
     * Retorna os dados do perfil do usuário autenticado.
     */
    @GetMapping("/me")
    public ResponseEntity<Void> obterPerfil() {
        return ResponseEntity.ok().build();
    }

    /**
     * Atualiza os dados pessoais do usuário autenticado (nome, e-mail, telefone).
     */
    @PutMapping("/me")
    public ResponseEntity<Void> atualizarPerfil(@RequestBody Map<String, Object> body) {
        return ResponseEntity.ok().build();
    }

    /**
     * Altera a senha do usuário, exigindo a senha atual para confirmação.
     */
    @PatchMapping("/me/senha")
    public ResponseEntity<Void> alterarSenha(@RequestBody Map<String, String> body) {
        return ResponseEntity.ok().build();
    }

    /**
     * Remove a conta do usuário autenticado do sistema.
     * Futuramente: desativar a conta em vez de excluir para preservar histórico de pedidos.
     * Futuramente: cancelar pedidos pendentes antes de remover a conta.
     */
    @DeleteMapping("/me")
    public ResponseEntity<Void> deletarConta() {
        return ResponseEntity.noContent().build();
    }

    /**
     * Lista os endereços de entrega salvos pelo usuário.
     */
    @GetMapping("/me/enderecos")
    public ResponseEntity<Void> listarEnderecos() {
        return ResponseEntity.ok().build();
    }

    /**
     * Adiciona um novo endereço de entrega ao perfil do usuário.
     */
    @PostMapping("/me/enderecos")
    public ResponseEntity<Void> adicionarEndereco(@RequestBody Map<String, Object> body) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Remove um endereço de entrega do perfil do usuário.
     */
    @DeleteMapping("/me/enderecos/{enderecoId}")
    public ResponseEntity<Void> removerEndereco(@PathVariable Long enderecoId) {
        return ResponseEntity.noContent().build();
    }
}
