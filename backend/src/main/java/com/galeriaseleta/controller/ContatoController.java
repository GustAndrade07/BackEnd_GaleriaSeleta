package com.galeriaseleta.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Gerencia o formulário de contato e a inscrição na newsletter.
 *
 * Futuramente: injetar ContatoService para delegar o envio de e-mails (ex: via JavaMailSender / SendGrid).
 * Futuramente: salvar mensagens em banco de dados para histórico e controle administrativo.
 * Futuramente: adicionar proteção contra spam (ex: verificação de CAPTCHA, rate limiting por IP).
 */
@RestController
@RequestMapping("/api")
public class ContatoController {

    /**
     * Recebe uma mensagem do formulário de contato do site.
     * Campos esperados: nome, sobrenome, email, telefone, mensagem.
     * Futuramente: encaminhar o conteúdo por e-mail para a equipe da Galeria Seleta.
     * Futuramente: enviar e-mail de confirmação ao remetente.
     */
    @PostMapping("/contato")
    public ResponseEntity<Void> enviarMensagem(@RequestBody Map<String, Object> body) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Inscreve um e-mail na lista de newsletter da loja.
     * Futuramente: verificar duplicidade antes de salvar.
     * Futuramente: enviar e-mail de boas-vindas com cupom de desconto para novos inscritos.
     */
    @PostMapping("/newsletter/inscrever")
    public ResponseEntity<Void> inscreverNewsletter(@RequestBody Map<String, Object> body) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Cancela a inscrição de um e-mail na newsletter.
     * Futuramente: implementar via token enviado no rodapé dos e-mails (unsubscribe link).
     * Query param: email.
     */
    @DeleteMapping("/newsletter/cancelar")
    public ResponseEntity<Void> cancelarNewsletter(@RequestParam String email) {
        return ResponseEntity.noContent().build();
    }
}
