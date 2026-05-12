package com.galeriaseleta.service;

import com.galeriaseleta.model.Carrinho;
import com.galeriaseleta.model.Produto;
import com.galeriaseleta.model.Usuario;
import com.galeriaseleta.repository.CarrinhoRepository;
import com.galeriaseleta.repository.ProdutoRepository;
import com.galeriaseleta.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository,
                           ProdutoRepository produtoRepository,
                           UsuarioRepository usuarioRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Carrinho> buscarOuCriar(Long usuarioId) {
        return carrinhoRepository.findByUsuarioId(usuarioId.intValue());
    }

    public Carrinho adicionarItem(Long usuarioId, Long produtoId, Integer quantidade) {
        // Se o produto já está no carrinho, retorna o existente
        return carrinhoRepository
                .findByUsuarioIdAndProdutoId(usuarioId.intValue(), produtoId.intValue())
                .orElseGet(() -> {
                    Usuario usuario = usuarioRepository.findById(usuarioId.intValue())
                            .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + usuarioId));
                    Produto produto = produtoRepository.findById(produtoId.intValue())
                            .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + produtoId));

                    Carrinho item = new Carrinho();
                    item.setUsuario(usuario);
                    item.setProduto(produto);
                    return carrinhoRepository.save(item);
                });
    }

    public void removerItem(Long carrinhoId, Long itemId) {
        carrinhoRepository.deleteById(itemId.intValue());
    }

    public void atualizarQuantidade(Long itemId, Integer quantidade) {
        // Modelo atual não possui campo quantidade; operação ignorada
    }

    public void limpar(Long usuarioId) {
        List<Carrinho> itens = carrinhoRepository.findByUsuarioId(usuarioId.intValue());
        carrinhoRepository.deleteAll(itens);
    }

    public double calcularTotal(Long usuarioId) {
        return carrinhoRepository.findByUsuarioId(usuarioId.intValue())
                .stream()
                .mapToDouble(item -> item.getProduto().getPreco().doubleValue())
                .sum();
    }
}
