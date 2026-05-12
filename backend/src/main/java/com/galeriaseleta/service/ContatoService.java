package com.galeriaseleta.service;

import com.galeriaseleta.model.Contato;
import com.galeriaseleta.model.Newsletter;
import com.galeriaseleta.repository.ContatoRepository;
import com.galeriaseleta.repository.NewsletterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ContatoService {

    private final ContatoRepository contatoRepository;
    private final NewsletterRepository newsletterRepository;

    public ContatoService(ContatoRepository contatoRepository, NewsletterRepository newsletterRepository) {
        this.contatoRepository = contatoRepository;
        this.newsletterRepository = newsletterRepository;
    }

    public void enviarMensagem(Map<String, Object> dados) {
        Contato contato = new Contato();
        contato.setNome((String) dados.get("nome"));
        contato.setSobrenome((String) dados.get("sobrenome"));
        contato.setEmail((String) dados.get("email"));
        contato.setTelefone((String) dados.get("telefone"));
        contato.setMensagem((String) dados.get("mensagem"));
        contatoRepository.save(contato);
    }

    public List<Contato> listarMensagens() {
        return contatoRepository.findAll();
    }

    public void deletarMensagem(Long id) {
        contatoRepository.deleteById(id.intValue());
    }

    public void inscreverNewsletter(Map<String, Object> dados) {
        String email = (String) dados.get("email");
        newsletterRepository.findByEmail(email).ifPresentOrElse(
                inscricao -> {
                    inscricao.setAtivo(true);
                    newsletterRepository.save(inscricao);
                },
                () -> {
                    Newsletter inscricao = new Newsletter();
                    inscricao.setEmail(email);
                    newsletterRepository.save(inscricao);
                }
        );
    }

    public void cancelarNewsletter(String email) {
        newsletterRepository.findByEmail(email).ifPresent(inscricao -> {
            inscricao.setAtivo(false);
            newsletterRepository.save(inscricao);
        });
    }
}
