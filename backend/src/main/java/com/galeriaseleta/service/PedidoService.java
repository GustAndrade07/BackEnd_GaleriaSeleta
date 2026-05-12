package com.galeriaseleta.service;

import com.galeriaseleta.model.*;
import com.galeriaseleta.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;
    private final ProdutoRepository produtoRepository;
    private final CupomRepository cupomRepository;
    private final OpcaoFreteRepository opcaoFreteRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                         ItemPedidoRepository itemPedidoRepository,
                         UsuarioRepository usuarioRepository,
                         EnderecoRepository enderecoRepository,
                         ProdutoRepository produtoRepository,
                         CupomRepository cupomRepository,
                         OpcaoFreteRepository opcaoFreteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.enderecoRepository = enderecoRepository;
        this.produtoRepository = produtoRepository;
        this.cupomRepository = cupomRepository;
        this.opcaoFreteRepository = opcaoFreteRepository;
    }

    public List<Pedido> listar(String status) {
        if (status != null && !status.isBlank()) {
            return pedidoRepository.findByStatus(status);
        }
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado: " + id));
    }

    /**
     * Cria um pedido a partir do checkout.
     * Espera no body: usuarioId, enderecoId, produtoIds (lista), freteId (opcional), codigoCupom (opcional).
     */
    public Pedido criar(Map<String, Object> dados) {
        Integer usuarioId = ((Number) dados.get("usuarioId")).intValue();
        Integer enderecoId = ((Number) dados.get("enderecoId")).intValue();

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + usuarioId));
        Endereco endereco = enderecoRepository.findById(enderecoId)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado: " + enderecoId));

        @SuppressWarnings("unchecked")
        List<Integer> produtoIds = (List<Integer>) dados.get("produtoIds");

        List<Produto> produtos = produtoIds.stream()
                .map(pid -> produtoRepository.findById(pid)
                        .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + pid)))
                .toList();

        BigDecimal subtotal = produtos.stream()
                .map(Produto::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal valorFrete = BigDecimal.ZERO;
        OpcaoFrete frete = null;
        if (dados.containsKey("freteId") && dados.get("freteId") != null) {
            Integer freteId = ((Number) dados.get("freteId")).intValue();
            frete = opcaoFreteRepository.findById(freteId).orElse(null);
            if (frete != null) valorFrete = frete.getPreco();
        }

        BigDecimal desconto = BigDecimal.ZERO;
        Cupom cupom = null;
        if (dados.containsKey("codigoCupom") && dados.get("codigoCupom") != null) {
            cupom = cupomRepository.findByCodigo((String) dados.get("codigoCupom")).orElse(null);
            if (cupom != null && Boolean.TRUE.equals(cupom.getAtivo())) {
                desconto = calcularDesconto(subtotal, cupom);
            }
        }

        BigDecimal total = subtotal.add(valorFrete).subtract(desconto);

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setEndereco(endereco);
        pedido.setCupom(cupom);
        pedido.setFrete(frete);
        pedido.setSubtotal(subtotal);
        pedido.setValorFrete(valorFrete);
        pedido.setDesconto(desconto);
        pedido.setTotal(total);
        pedido.setStatus("pendente");

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        for (Produto produto : produtos) {
            ItemPedido item = new ItemPedido();
            item.setPedido(pedidoSalvo);
            item.setProduto(produto);
            item.setPrecoPago(produto.getPreco());
            itemPedidoRepository.save(item);
        }

        return pedidoSalvo;
    }

    public void cancelar(Long id) {
        Pedido pedido = buscarPorId(id);
        pedido.setStatus("cancelado");
        pedidoRepository.save(pedido);
    }

    public void atualizarStatus(Long id, String status) {
        Pedido pedido = buscarPorId(id);
        pedido.setStatus(status);
        pedidoRepository.save(pedido);
    }

    private BigDecimal calcularDesconto(BigDecimal subtotal, Cupom cupom) {
        if ("percentual".equals(cupom.getTipoDesconto())) {
            return subtotal.multiply(cupom.getValorDesconto()).divide(BigDecimal.valueOf(100));
        }
        return cupom.getValorDesconto();
    }
}
