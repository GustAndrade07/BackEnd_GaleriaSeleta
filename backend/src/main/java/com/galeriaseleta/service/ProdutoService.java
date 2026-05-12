package com.galeriaseleta.service;

import com.galeriaseleta.model.Produto;
import com.galeriaseleta.repository.CategoriaRepository;
import com.galeriaseleta.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public List<Produto> listarAtivos() {
        return produtoRepository.findByStatus("disponivel");
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + id));
    }

    public List<Produto> buscarPorCategoria(Long categoriaId) {
        return produtoRepository.findByCategoriaId(categoriaId.intValue());
    }

    public List<Produto> buscarPorNome(String nome) {
        return produtoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Produto> listarNovidades() {
        return produtoRepository.findByNovidade(true);
    }

    public List<Produto> listarPorMenorPreco() {
        return produtoRepository.findAllOrderByPrecoAsc();
    }

    public List<Produto> listarPorMaiorPreco() {
        return produtoRepository.findAllOrderByPrecoDesc();
    }

    public Produto salvar(Map<String, Object> dados) {
        Produto produto = new Produto();
        preencherProduto(produto, dados);
        return produtoRepository.save(produto);
    }

    public Produto atualizar(Long id, Map<String, Object> dados) {
        Produto produto = buscarPorId(id);
        preencherProduto(produto, dados);
        return produtoRepository.save(produto);
    }

    public void deletar(Long id) {
        produtoRepository.deleteById(id.intValue());
    }

    private void preencherProduto(Produto produto, Map<String, Object> dados) {
        if (dados.containsKey("nome")) produto.setNome((String) dados.get("nome"));
        if (dados.containsKey("descricao")) produto.setDescricao((String) dados.get("descricao"));
        if (dados.containsKey("status")) produto.setStatus((String) dados.get("status"));
        if (dados.containsKey("novidade")) produto.setNovidade((Boolean) dados.get("novidade"));

        if (dados.containsKey("preco") && dados.get("preco") != null) {
            produto.setPreco(new BigDecimal(dados.get("preco").toString()));
        }

        if (dados.containsKey("categoriaId") && dados.get("categoriaId") != null) {
            Integer catId = ((Number) dados.get("categoriaId")).intValue();
            categoriaRepository.findById(catId).ifPresent(produto::setCategoria);
        }
    }
}
