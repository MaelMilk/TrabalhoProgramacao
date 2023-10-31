package model;

public class Login {
    private String idlogin, senha;
    private int idusuario;

    public Login(){}

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getidlogin() {
        return idlogin;
    }

    public void setidlogin(String idlogin) {
        this.idlogin = idlogin;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
