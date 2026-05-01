package com.galeriaseleta.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.galeriaseleta.repository.PedidoRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

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
