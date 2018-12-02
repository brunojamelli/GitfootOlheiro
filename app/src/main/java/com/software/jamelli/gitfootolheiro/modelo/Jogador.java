package com.software.jamelli.gitfootolheiro.modelo;

import com.google.android.gms.maps.model.LatLng;

public class Jogador extends Pessoa{
    private String pe_melhor;
    private String posicao;
    private Double pretencao_salarial;
    private Double pretencao_contratual;

    public Jogador() {
    }

    public Jogador(String uid,String photoUrl, String email, String nome, String pe_melhor, String posicao, Double pretencao_salarial, Double pretencao_contratual) {
        super(uid,photoUrl, email, nome);
        this.pe_melhor = pe_melhor;
        this.posicao = posicao;
        this.pretencao_salarial = pretencao_salarial;
        this.pretencao_contratual = pretencao_contratual;
    }

    public Jogador(String photoUrl, String email, String nome) {
        super(photoUrl, email, nome);
    }

    public String getPe_melhor() {
        return pe_melhor;
    }

    public void setPe_melhor(String pe_melhor) {
        this.pe_melhor = pe_melhor;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public Double getPretencao_salarial() {
        return pretencao_salarial;
    }

    public void setPretencao_salarial(Double pretencao_salarial) {
        this.pretencao_salarial = pretencao_salarial;
    }

    public Double getPretencao_contratual() {
        return pretencao_contratual;
    }

    public void setPretencao_contratual(Double pretencao_contratual) {
        this.pretencao_contratual = pretencao_contratual;
    }

    @Override
    public String toString() {
        return super.toString()+ "pe_melhor=" + pe_melhor + '\'' +
                ", posicao='" + posicao + '\'' +
                ", pretencao_salarial=" + pretencao_salarial +
                ", pretencao_contratual=" + pretencao_contratual;
    }
}
