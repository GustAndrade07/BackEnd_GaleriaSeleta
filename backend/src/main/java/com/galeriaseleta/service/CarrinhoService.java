package com.galeriaseleta.service;

import com.galeriaseleta.repository.CarrinhoRepository;
import org.springframework.stereotype.Service;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository) {
        this.carrinhoRepository = carrinhoRepository;
    }

    /** Retorna o carrinho do usuário ou cria um novo se não existir. */
    public Object buscarOuCriar(Long usuarioId) {
        throw new UnsupportedOperationException("Não implementado");
    }

    /** Adiciona o produto ao carrinho. Incrementa a quantidade se o item já existir. */
    public Object adicionarItem(Long usuarioId, Long produtoId, Integer quantidade) {
        throw new UnsupportedOperationException("Não implementado");
    }

    public void removerItem(Long carrinhoId, Long itemId) {
        throw new UnsupportedOperationException("Não implementado");
    }

    public void atualizarQuantidade(Long itemId, Integer quantidade) {
        throw new UnsupportedOperationException("Não implementado");
    }

    public void limpar(Long usuarioId) {
        throw new UnsupportedOperationException("Não implementado");
    }

    /** Soma os itens usando preço com desconto quando disponível; caso contrário, usa o preço original. */
    public double calcularTotal(Long carrinhoId) {
        throw new UnsupportedOperationException("Não implementado");
    }
}
