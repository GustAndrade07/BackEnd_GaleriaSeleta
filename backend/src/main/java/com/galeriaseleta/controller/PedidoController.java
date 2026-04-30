package com.galeriaseleta.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Gerencia os pedidos de compra dos usuários.
 *
 * Futuramente: injetar PedidoService para delegar a lógica de negócio.
 * Futuramente: integrar com gateway de pagamento (ex: Mercado Pago, Stripe).
 * Futuramente: enviar e-mail de confirmação ao criar ou atualizar o status do pedido.
 *
 * Status possíveis: AGUARDANDO_PAGAMENTO → CONFIRMADO → EM_SEPARACAO → ENVIADO → ENTREGUE | CANCELADO.
 */
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    /**
     * Lista todos os pedidos do usuário autenticado.
     * Query params opcionais: status.
     */
    @GetMapping
    public ResponseEntity<Void> listarPedidos(
            @RequestParam(required = false) String status) {
        return ResponseEntity.ok().build();
    }

    /**
     * Retorna os detalhes completos de um pedido do usuário autenticado.
     * Futuramente: verificar se o pedido pertence ao usuário antes de retornar.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Void> buscarPedido(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    /**
     * Cria um novo pedido (finalização do checkout).
     * Futuramente: verificar disponibilidade de estoque antes de confirmar.
     * Futuramente: iniciar o fluxo de pagamento e retornar URL/dados de pagamento.
     */
    @PostMapping
    public ResponseEntity<Void> criarPedido(@RequestBody Map<String, Object> body) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Cancela um pedido do usuário autenticado.
     * Futuramente: permitir cancelamento apenas em status iniciais (ex: AGUARDANDO_PAGAMENTO, CONFIRMADO).
     * Futuramente: iniciar estorno automático caso o pagamento já tenha sido processado.
     */
    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelarPedido(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    /**
     * Atualiza o status de um pedido — uso exclusivo de administradores.
     * Body esperado: { "status": "ENVIADO" }.
     * Futuramente: disparar notificação ao cliente em cada mudança de status.
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> atualizarStatusPedido(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        return ResponseEntity.ok().build();
    }
}
