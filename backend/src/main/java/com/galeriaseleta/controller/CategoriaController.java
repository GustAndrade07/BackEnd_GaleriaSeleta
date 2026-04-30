package com.galeriaseleta.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Gerencia as categorias de produtos da loja (ex: Moletons, Calças, Camisetas, Calçados).
 *
 * Futuramente: injetar CategoriaService para delegar a lógica de negócio.
 */
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    /**
     * Lista todas as categorias cadastradas.
     * Usado pelo frontend para popular o menu de filtros na tela de produtos.
     */
    @GetMapping
    public ResponseEntity<Void> listarCategorias() {
        return ResponseEntity.ok().build();
    }

    /**
     * Retorna os detalhes de uma categoria específica.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Void> buscarCategoria(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    /**
     * Lista todos os produtos pertencentes a uma categoria específica.
     */
    @GetMapping("/{id}/produtos")
    public ResponseEntity<Void> listarProdutosPorCategoria(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    /**
     * Cadastra uma nova categoria de produtos.
     * Restrito a administradores.
     */
    @PostMapping
    public ResponseEntity<Void> criarCategoria(@RequestBody Map<String, Object> body) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Atualiza os dados de uma categoria existente.
     * Restrito a administradores.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarCategoria(
            @PathVariable Long id,
            @RequestBody Map<String, Object> body) {
        return ResponseEntity.ok().build();
    }

    /**
     * Remove uma categoria do sistema.
     * Futuramente: verificar se existem produtos vinculados antes de deletar.
     * Restrito a administradores.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
