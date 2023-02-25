package Administracao;

public class Administrador {
    private String usuario;
    private String senha;

    public Administrador() {
        this.usuario = "adm";
        this.senha = "adm";
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}