package com.galeriaseleta.service;

import com.galeriaseleta.model.Usuario;
import com.galeriaseleta.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario login(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));

        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new RuntimeException("Credenciais inválidas");
        }

        return usuario;
    }

    public Usuario registrar(Map<String, Object> dados) {
        String email = (String) dados.get("email");

        if (usuarioRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado: " + email);
        }

        Usuario usuario = new Usuario();
        usuario.setNome((String) dados.get("nome"));
        usuario.setEmail(email);
        usuario.setSenha(passwordEncoder.encode((String) dados.get("senha")));

        if (dados.containsKey("telefone")) {
            usuario.setTelefone((String) dados.get("telefone"));
        }

        return usuarioRepository.save(usuario);
    }

    public void logout() {
        // Sessão gerenciada pelo cliente; sem estado no servidor por enquanto
    }

    public Object refreshToken(String refreshToken) {
        throw new UnsupportedOperationException("JWT não implementado ainda");
    }

    public void esqueceuSenha(String email) {
        usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("E-mail não encontrado"));
        // Envio de e-mail de recuperação será implementado com JWT
    }

    public void redefinirSenha(String token, String novaSenha) {
        throw new UnsupportedOperationException("Redefinição de senha requer JWT");
    }
}
