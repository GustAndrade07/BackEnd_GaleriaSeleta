package com.galeriaseleta.controller;

import com.galeriaseleta.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

// Fluxo de status: AGUARDANDO_PAGAMENTO → CONFIRMADO → EM_SEPARACAO → ENVIADO → ENTREGUE | CANCELADO
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    /** Lista os pedidos do usuário autenticado. Param opcional: status. */
    @GetMapping
    public ResponseEntity<Object> listarPedidos(@RequestParam(required = false) String status) {
        return ResponseEntity.ok(pedidoService.listar(status));
    }

    /** Retorna os detalhes de um pedido pelo ID. */
    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPedido(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.buscarPorId(id));
    }

    /** Cria um novo pedido (finalização do checkout). */
    @PostMapping
    public ResponseEntity<Object> criarPedido(@RequestBody Map<String, Object> body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.criar(body));
    }

    /** Cancela um pedido do usuário autenticado. */
    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelarPedido(@PathVariable Long id) {
        pedidoService.cancelar(id);
        return ResponseEntity.ok().build();
    }

    /** Atualiza o status de um pedido. Body: { status }. Uso administrativo. */
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> atualizarStatusPedido(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        pedidoService.atualizarStatus(id, body.get("status"));
        return ResponseEntity.ok().build();
    }
}
