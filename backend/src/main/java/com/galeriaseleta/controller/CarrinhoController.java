package com.galeriaseleta.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Gerencia o carrinho de compras do usuário autenticado.
 *
 * Futuramente: injetar CarrinhoService para delegar a lógica de negócio.
 * Futuramente: suportar carrinho temporário (Redis/sessão) para usuários não autenticados,
 *              com merge automático dos itens ao fazer login.
 * Futuramente: verificar disponibilidade de estoque ao adicionar ou atualizar itens.
 */
@RestController
@RequestMapping("/api/carrinho")
public class CarrinhoController {

    /**
     * Retorna o carrinho atual do usuário com todos os itens e o total calculado.
     */
    @GetMapping
    public ResponseEntity<Void> obterCarrinho() {
        return ResponseEntity.ok().build();
    }

    /**
     * Adiciona um produto ao carrinho.
     * Futuramente: se o produto (com mesmo tamanho/cor) já existir, incrementar a quantidade.
     */
    @PostMapping("/itens")
    public ResponseEntity<Void> adicionarItem(@RequestBody Map<String, Object> body) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Atualiza a quantidade de um item já existente no carrinho.
     * Futuramente: remover o item automaticamente se a quantidade for zero.
     */
    @PutMapping("/itens/{itemId}")
    public ResponseEntity<Void> atualizarItem(
            @PathVariable Long itemId,
            @RequestBody Map<String, Object> body) {
        return ResponseEntity.ok().build();
    }

    /**
     * Remove um item específico do carrinho.
     */
    @DeleteMapping("/itens/{itemId}")
    public ResponseEntity<Void> removerItem(@PathVariable Long itemId) {
        return ResponseEntity.noContent().build();
    }

    /**
     * Esvazia completamente o carrinho do usuário.
     */
    @DeleteMapping
    public ResponseEntity<Void> limparCarrinho() {
        return ResponseEntity.noContent().build();
    }
}
