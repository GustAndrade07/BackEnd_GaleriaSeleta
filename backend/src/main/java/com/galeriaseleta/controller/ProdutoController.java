package com.galeriaseleta.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Gerencia o catálogo de produtos da loja.
 *
 * Futuramente: injetar ProdutoService para delegar a lógica de negócio.
 * Futuramente: adicionar paginação com Pageable nos endpoints de listagem.
 */
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    /**
     * Lista todos os produtos ativos com suporte a filtro por categoria e ordenação.
     * Query params: categoriaId, ordenacao (menor-preco | maior-preco | novidades), status.
     */
    @GetMapping
    public ResponseEntity<Void> listarProdutos(
            @RequestParam(required = false) Long categoriaId,
            @RequestParam(required = false, defaultValue = "padrao") String ordenacao,
            @RequestParam(required = false, defaultValue = "ativo") String status) {
        return ResponseEntity.ok().build();
    }

    /**
     * Retorna os detalhes completos de um produto pelo seu ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Void> buscarProduto(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    /**
     * Cadastra um novo produto no catálogo.
     * Restrito a administradores.
     */
    @PostMapping
    public ResponseEntity<Void> criarProduto(@RequestBody Map<String, Object> body) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Substitui completamente os dados de um produto existente.
     * Restrito a administradores.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarProduto(
            @PathVariable Long id,
            @RequestBody Map<String, Object> body) {
        return ResponseEntity.ok().build();
    }

    /**
     * Altera apenas o status de visibilidade de um produto (ativo/inativo).
     * Body esperado: { "status": "ativo" } ou { "status": "inativo" }.
     * Restrito a administradores.
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> atualizarStatusProduto(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        return ResponseEntity.ok().build();
    }

    /**
     * Remove um produto do catálogo permanentemente.
     * Futuramente: considerar soft delete para preservar histórico de pedidos.
     * Restrito a administradores.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
