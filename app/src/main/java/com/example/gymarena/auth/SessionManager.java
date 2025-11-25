package com.example.gymarena.auth;

public class SessionManager {
    private String usuarioActivo;

    public SessionManager(String usuarioActivo) {
        this.usuarioActivo = usuarioActivo;
    }

    public String getUsuarioActivo() {
        return usuarioActivo;
    }

    public void setUsuarioActivo(String usuarioActivo) {
        this.usuarioActivo = usuarioActivo;
    }
}
