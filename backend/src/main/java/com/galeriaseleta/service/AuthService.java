package com.galeriaseleta.service;

import com.galeriaseleta.model.Role;
import com.galeriaseleta.model.Usuario;
import com.galeriaseleta.repository.UsuarioRepository;
import com.galeriaseleta.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UsuarioRepository usuarioRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder   = passwordEncoder;
        this.jwtUtil           = jwtUtil;
    }

    public Map<String, Object> login(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));

        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new RuntimeException("Credenciais inválidas");
        }

        String token = jwtUtil.generateToken(usuario.getEmail(), usuario.getRole().name());
        return buildAuthResponse(token, usuario);
    }

    public Map<String, Object> registrar(Map<String, Object> dados) {
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

        // Permite criar admin via campo "role" (uso interno / seed)
        if ("ROLE_ADMIN".equals(dados.get("role"))) {
            usuario.setRole(Role.ROLE_ADMIN);
        }

        usuarioRepository.save(usuario);

        String token = jwtUtil.generateToken(usuario.getEmail(), usuario.getRole().name());
        return buildAuthResponse(token, usuario);
    }

    public void logout() {
        // JWT é stateless; o cliente descarta o token
    }

    public Object refreshToken(String refreshToken) {
        throw new UnsupportedOperationException("Refresh token não implementado");
    }

    public void esqueceuSenha(String email) {
        usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("E-mail não encontrado"));
        // Envio de e-mail de recuperação pendente
    }

    public void redefinirSenha(String token, String novaSenha) {
        throw new UnsupportedOperationException("Redefinição de senha pendente");
    }

    private Map<String, Object> buildAuthResponse(String token, Usuario usuario) {
        return Map.of(
                "token", token,
                "tipo", "Bearer",
                "usuario", Map.of(
                        "id",    usuario.getId(),
                        "nome",  usuario.getNome(),
                        "email", usuario.getEmail(),
                        "role",  usuario.getRole().name()
                )
        );
    }
}
