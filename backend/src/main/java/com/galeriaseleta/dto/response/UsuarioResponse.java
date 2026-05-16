package com.galeriaseleta.dto.response;

import com.galeriaseleta.model.Usuario;
import java.time.LocalDateTime;

public class UsuarioResponse {

    private Integer id;
    private String nome;
    private String email;
    private String telefone;
    private String role;
    private String token;
    private LocalDateTime criadoEm;

    public static UsuarioResponse from(Usuario usuario) {
        return from(usuario, null);
    }

    public static UsuarioResponse from(Usuario usuario, String token) {
        UsuarioResponse dto = new UsuarioResponse();
        dto.id       = usuario.getId();
        dto.nome     = usuario.getNome();
        dto.email    = usuario.getEmail();
        dto.telefone = usuario.getTelefone();
        dto.role     = usuario.getRole().name();
        dto.token    = token;
        dto.criadoEm = usuario.getCriadoEm();
        return dto;
    }

    public Integer getId()            { return id; }
    public String getNome()           { return nome; }
    public String getEmail()          { return email; }
    public String getTelefone()       { return telefone; }
    public String getRole()           { return role; }
    public String getToken()          { return token; }
    public LocalDateTime getCriadoEm(){ return criadoEm; }
}
