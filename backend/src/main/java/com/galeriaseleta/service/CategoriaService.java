package com.galeriaseleta.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.galeriaseleta.repository.CategoriaRepository;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Object> listarTodas() {
        throw new UnsupportedOperationException("Não implementado");
    }

    public Object buscarPorId(Long id) {
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
