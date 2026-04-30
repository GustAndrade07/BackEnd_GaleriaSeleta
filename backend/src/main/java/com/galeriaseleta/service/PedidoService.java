package com.galeriaseleta.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PedidoService {

    public List<Object> listar(String status) {
        throw new UnsupportedOperationException("Não implementado");
    }

    public Object buscarPorId(Long id) {
        throw new UnsupportedOperationException("Não implementado");
    }

    public Object criar(Map<String, Object> dados) {
        throw new UnsupportedOperationException("Não implementado");
    }

    public void cancelar(Long id) {
        throw new UnsupportedOperationException("Não implementado");
    }

    public void atualizarStatus(Long id, String status) {
        throw new UnsupportedOperationException("Não implementado");
    }
}
