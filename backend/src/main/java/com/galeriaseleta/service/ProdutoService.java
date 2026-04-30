package com.galeriaseleta.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProdutoService {

    public List<Object> listarTodos() {
        throw new UnsupportedOperationException("Não implementado");
    }

    public List<Object> listarAtivos() {
        throw new UnsupportedOperationException("Não implementado");
    }

    public Object buscarPorId(Long id) {
        throw new UnsupportedOperationException("Não implementado");
    }

    public List<Object> buscarPorCategoria(Long categoriaId) {
        throw new UnsupportedOperationException("Não implementado");
    }

    public List<Object> buscarPorNome(String nome) {
        throw new UnsupportedOperationException("Não implementado");
    }

    /** Retorna os ativos ordenados do mais recente para o mais antigo. */
    public List<Object> listarNovidades() {
        throw new UnsupportedOperationException("Não implementado");
    }

    /** Ordena pelo preço final (considera desconto quando disponível). */
    public List<Object> listarPorMenorPreco() {
        throw new UnsupportedOperationException("Não implementado");
    }

    /** Ordena pelo preço final (considera desconto quando disponível). */
    public List<Object> listarPorMaiorPreco() {
        throw new UnsupportedOperationException("Não implementado");
    }

    public Object salvar(Map<String, Object> dados) {
        throw new UnsupportedOperationException("Não implementado");
    }

    public Object atualizar(Long id, Map<String, Object> dados) {
        throw new UnsupportedOperationException("Não implementado");
    }

    public void deletar(Long id) {
        throw new UnsupportedOperationException("Não implementado");
    }
}
