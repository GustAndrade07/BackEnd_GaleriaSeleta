package com.galeriaseleta.controller;

import com.galeriaseleta.service.CategoriaService;
import com.galeriaseleta.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;
    private final ProdutoService produtoService;

    public CategoriaController(CategoriaService categoriaService, ProdutoService produtoService) {
        this.categoriaService = categoriaService;
        this.produtoService = produtoService;
    }

    /** Lista todas as categorias. */
    @GetMapping
    public ResponseEntity<Object> listarCategorias() {
        return ResponseEntity.ok(categoriaService.listarTodas());
    }

    /** Retorna os detalhes de uma categoria pelo ID. */
    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarCategoria(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }

    /** Lista os produtos de uma categoria específica. */
    @GetMapping("/{id}/produtos")
    public ResponseEntity<Object> listarProdutosPorCategoria(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarPorCategoria(id));
    }

    /** Cadastra uma nova categoria. */
    @PostMapping
    public ResponseEntity<Object> criarCategoria(@RequestBody Map<String, Object> body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.salvar(body));
    }

    /** Atualiza os dados de uma categoria existente. */
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarCategoria(
            @PathVariable Long id,
            @RequestBody Map<String, Object> body) {
        return ResponseEntity.ok(categoriaService.atualizar(id, body));
    }

    /** Remove uma categoria pelo ID. */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
