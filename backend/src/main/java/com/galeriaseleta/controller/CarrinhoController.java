package com.galeriaseleta.controller;

import com.galeriaseleta.service.CarrinhoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/carrinho")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    /** Retorna o carrinho do usuário autenticado com itens e total. O ID do usuário virá do contexto de autenticação. */
    @GetMapping
    public ResponseEntity<Object> obterCarrinho() {
        return ResponseEntity.ok(carrinhoService.buscarOuCriar(0L));
    }

    /** Adiciona um produto ao carrinho. Body: { produtoId, quantidade, tamanho?, cor? }. */
    @PostMapping("/itens")
    public ResponseEntity<Object> adicionarItem(@RequestBody Map<String, Object> body) {
        Long produtoId = ((Number) body.get("produtoId")).longValue();
        Integer quantidade = (Integer) body.get("quantidade");
        return ResponseEntity.status(HttpStatus.CREATED).body(carrinhoService.adicionarItem(0L, produtoId, quantidade));
    }

    /** Atualiza a quantidade de um item do carrinho. Body: { quantidade }. */
    @PutMapping("/itens/{itemId}")
    public ResponseEntity<Void> atualizarItem(
            @PathVariable Long itemId,
            @RequestBody Map<String, Object> body) {
        Integer quantidade = (Integer) body.get("quantidade");
        carrinhoService.atualizarQuantidade(itemId, quantidade);
        return ResponseEntity.ok().build();
    }

    /** Remove um item do carrinho pelo ID. */
    @DeleteMapping("/itens/{itemId}")
    public ResponseEntity<Void> removerItem(@PathVariable Long itemId) {
        carrinhoService.removerItem(0L, itemId);
        return ResponseEntity.noContent().build();
    }

    /** Esvazia completamente o carrinho do usuário. */
    @DeleteMapping
    public ResponseEntity<Void> limparCarrinho() {
        carrinhoService.limpar(0L);
        return ResponseEntity.noContent().build();
    }
}
