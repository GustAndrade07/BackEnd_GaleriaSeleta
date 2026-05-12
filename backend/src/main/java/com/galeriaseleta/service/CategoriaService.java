package com.galeriaseleta.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.galeriaseleta.model.Categoria;
import com.galeriaseleta.repository.CategoriaRepository;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada: " + id));
    }

    public Categoria salvar(Map<String, Object> dados) {
        Categoria categoria = new Categoria();
        categoria.setNome((String) dados.get("nome"));
        categoria.setNomeUrl((String) dados.get("nomeUrl"));

        if (dados.containsKey("categoriaMaeId") && dados.get("categoriaMaeId") != null) {
            Integer maeId = ((Number) dados.get("categoriaMaeId")).intValue();
            categoriaRepository.findById(maeId).ifPresent(categoria::setCategoriaMae);
        }

        if (dados.containsKey("ativo")) {
            categoria.setAtivo((Boolean) dados.get("ativo"));
        }

        return categoriaRepository.save(categoria);
    }

    public Categoria atualizar(Long id, Map<String, Object> dados) {
        Categoria categoria = buscarPorId(id);

        if (dados.containsKey("nome")) categoria.setNome((String) dados.get("nome"));
        if (dados.containsKey("nomeUrl")) categoria.setNomeUrl((String) dados.get("nomeUrl"));
        if (dados.containsKey("ativo")) categoria.setAtivo((Boolean) dados.get("ativo"));

        if (dados.containsKey("categoriaMaeId")) {
            if (dados.get("categoriaMaeId") == null) {
                categoria.setCategoriaMae(null);
            } else {
                Integer maeId = ((Number) dados.get("categoriaMaeId")).intValue();
                categoriaRepository.findById(maeId).ifPresent(categoria::setCategoriaMae);
            }
        }

        return categoriaRepository.save(categoria);
    }

    public void deletar(Long id) {
        categoriaRepository.deleteById(id.intValue());
    }
}
