package com.galeriaseleta.controller;

import com.galeriaseleta.service.ContatoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ContatoController {

    private final ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    /** Recebe uma mensagem do formulário de contato. Campos: nome, sobrenome, email, telefone?, mensagem. */
    @PostMapping("/contato")
    public ResponseEntity<Void> enviarMensagem(@RequestBody Map<String, Object> body) {
        contatoService.enviarMensagem(body);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /** Inscreve um e-mail na newsletter. */
    @PostMapping("/newsletter/inscrever")
    public ResponseEntity<Void> inscreverNewsletter(@RequestBody Map<String, Object> body) {
        contatoService.inscreverNewsletter(body);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /** Cancela a inscrição de um e-mail na newsletter. Param: email. */
    @DeleteMapping("/newsletter/cancelar")
    public ResponseEntity<Void> cancelarNewsletter(@RequestParam String email) {
        contatoService.cancelarNewsletter(email);
        return ResponseEntity.noContent().build();
    }
}
