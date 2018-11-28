package com.software.jamelli.gitfootolheiro.modelo;

public class Olheiro extends Pessoa {
    private String time, empresa, empresario;

    public Olheiro() { }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getEmpresario() {
        return empresario;
    }

    public void setEmpresario(String empresario) {
        this.empresario = empresario;
    }

    public Olheiro(String uid,String photoUrl, String email, String nome, String time, String empresa, String empresario) {
        super(uid,photoUrl, email, nome);
        this.time = time;
        this.empresa = empresa;
        this.empresario = empresario;
    }

    public Olheiro(String photoUrl, String email, String nome) {
        super(photoUrl, email, nome);
    }
}
