package com.galeriaseleta.service;

import com.galeriaseleta.model.Endereco;
import com.galeriaseleta.model.Usuario;
import com.galeriaseleta.repository.EnderecoRepository;
import com.galeriaseleta.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UsuarioService(UsuarioRepository usuarioRepository, EnderecoRepository enderecoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public Usuario cadastrar(Map<String, Object> dados) {
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

    public Usuario login(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new RuntimeException("Senha incorreta");
        }

        return usuario;
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + id));
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + email));
    }

    public Usuario atualizar(Long id, Map<String, Object> dados) {
        Usuario usuario = buscarPorId(id);

        if (dados.containsKey("nome")) usuario.setNome((String) dados.get("nome"));
        if (dados.containsKey("telefone")) usuario.setTelefone((String) dados.get("telefone"));

        if (dados.containsKey("senha") && dados.get("senha") != null) {
            usuario.setSenha(passwordEncoder.encode((String) dados.get("senha")));
        }

        return usuarioRepository.save(usuario);
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id.intValue());
    }

    public List<Endereco> listarEnderecos(Long usuarioId) {
        return enderecoRepository.findByUsuarioId(usuarioId.intValue());
    }

    public Endereco adicionarEndereco(Long usuarioId, Map<String, Object> dados) {
        Usuario usuario = buscarPorId(usuarioId);

        Endereco endereco = new Endereco();
        endereco.setUsuario(usuario);
        endereco.setRua((String) dados.get("rua"));
        endereco.setCidade((String) dados.get("cidade"));
        endereco.setEstado((String) dados.get("estado"));
        endereco.setCep((String) dados.get("cep"));

        if (dados.containsKey("principal")) {
            endereco.setPrincipal((Boolean) dados.get("principal"));
        }

        return enderecoRepository.save(endereco);
    }

    public void removerEndereco(Long enderecoId) {
        enderecoRepository.deleteById(enderecoId.intValue());
    }
}
