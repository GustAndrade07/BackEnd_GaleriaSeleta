package com.galeriaseleta.dto.response;

import com.galeriaseleta.model.Carrinho;
import java.time.LocalDateTime;

public class CarrinhoItemResponse {

    private Integer id;
    private ProdutoResponse produto;
    private LocalDateTime adicionadoEm;

    public static CarrinhoItemResponse from(Carrinho carrinho) {
        CarrinhoItemResponse dto = new CarrinhoItemResponse();
        dto.id = carrinho.getId();
        dto.produto = ProdutoResponse.from(carrinho.getProduto());
        dto.adicionadoEm = carrinho.getAdicionadoEm();
        return dto;
    }

    public Integer getId() { return id; }
    public ProdutoResponse getProduto() { return produto; }
    public LocalDateTime getAdicionadoEm() { return adicionadoEm; }
}
