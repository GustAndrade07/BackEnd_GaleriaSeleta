package com.galeriaseleta.controller;

import com.galeriaseleta.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    /** Lista produtos com filtros opcionais. Roteia para o método de serviço adequado com base nos parâmetros. */
    @GetMapping
    public ResponseEntity<Object> listarProdutos(
            @RequestParam(required = false) Long categoriaId,
            @RequestParam(required = false, defaultValue = "padrao") String ordenacao,
            @RequestParam(required = false, defaultValue = "ativo") String status) {
        if (categoriaId != null) {
            return ResponseEntity.ok(produtoService.buscarPorCategoria(categoriaId));
        }
        return switch (ordenacao) {
            case "menor-preco" -> ResponseEntity.ok(produtoService.listarPorMenorPreco());
            case "maior-preco" -> ResponseEntity.ok(produtoService.listarPorMaiorPreco());
            case "novidades"   -> ResponseEntity.ok(produtoService.listarNovidades());
            default -> "todos".equals(status)
                    ? ResponseEntity.ok(produtoService.listarTodos())
                    : ResponseEntity.ok(produtoService.listarAtivos());
        };
    }

    /** Retorna os 8 produtos ativos mais recentes para o marquee da home. */
    @GetMapping("/novidades")
    public ResponseEntity<Object> listarNovidades() {
        return ResponseEntity.ok(produtoService.listarNovidades());
    }

    /** Busca produtos cujo nome contenha o termo informado. */
    @GetMapping("/busca")
    public ResponseEntity<Object> buscar(@RequestParam String termo) {
        return ResponseEntity.ok(produtoService.buscarPorNome(termo));
    }

    /** Retorna os detalhes de um produto pelo ID. */
    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarProduto(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    /** Cadastra um novo produto. */
    @PostMapping
    public ResponseEntity<Object> criarProduto(@RequestBody Map<String, Object> body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.salvar(body));
    }

    /** Atualiza os dados de um produto existente. */
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarProduto(
            @PathVariable Long id,
            @RequestBody Map<String, Object> body) {
        return ResponseEntity.ok(produtoService.atualizar(id, body));
    }

    /** Altera o status de visibilidade de um produto (ativo/inativo). */
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> atualizarStatusProduto(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        produtoService.atualizar(id, new java.util.HashMap<>(body));
        return ResponseEntity.ok().build();
    }

    /** Remove um produto pelo ID. */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
