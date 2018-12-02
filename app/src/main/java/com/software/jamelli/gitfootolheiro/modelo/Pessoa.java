package com.software.jamelli.gitfootolheiro.modelo;

import com.google.android.gms.maps.model.LatLng;

public class Pessoa {
    private String photoUrl;
    private String email;
    private String nome;
    private String Uid;
    private Localization localization;

    public Localization getLocalization() {
        return localization;
    }

    public void setLocalization(Localization localization) {
        this.localization = localization;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pessoa() {
    }

    public Pessoa(String photoUrl, String email, String nome) {
        this.photoUrl = photoUrl;
        this.email = email;
        this.nome = nome;
    }

    public Pessoa(String uid,String photoUrl, String email, String nome) {
        this.Uid = uid;
        this.photoUrl = photoUrl;
        this.email = email;
        this.nome = nome;

    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "photoUrl='" + photoUrl + '\'' +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", Uid='" + Uid + '\'' +
                ", localization=" + localization +
                '}';
    }
}
