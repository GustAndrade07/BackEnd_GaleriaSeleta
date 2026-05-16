package com.galeriaseleta.service;

import com.galeriaseleta.dto.request.AuthRegisterRequest;
import com.galeriaseleta.model.Usuario;
import com.galeriaseleta.repository.UsuarioRepository;
import com.galeriaseleta.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public Usuario login(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));

        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new RuntimeException("Credenciais inválidas");
        }

        return usuario;
    }

    public Usuario registrar(AuthRegisterRequest request) {
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado: " + request.getEmail());
        }

        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setSenha(passwordEncoder.encode(request.getSenha()));
        usuario.setTelefone(request.getTelefone());

        return usuarioRepository.save(usuario);
    }

    public String generateToken(Usuario usuario) {
        return jwtUtil.generateToken(usuario.getEmail(), usuario.getRole().name());
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
}
