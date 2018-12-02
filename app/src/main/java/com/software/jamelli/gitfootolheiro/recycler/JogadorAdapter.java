package com.software.jamelli.gitfootolheiro.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.software.jamelli.gitfootolheiro.modelo.Jogador;
import com.software.jamelli.gitfootolheiro.R;
import com.software.jamelli.gitfootolheiro.util.GlideUtil;

import java.util.List;

import br.com.concrete.canarinho.formatador.Formatador;
import br.com.concrete.canarinho.formatador.FormatadorValor;

public class JogadorAdapter extends RecyclerView.Adapter{
    Context context;
    List<Jogador> jogadores;
    public JogadorAdapter(Context c,List<Jogador> j){

        this.context = c;
        this.jogadores = j;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_jogador, parent, false);
        JogadorViewHolder holder = new JogadorViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final JogadorViewHolder jholder = (JogadorViewHolder) holder;
        final Jogador jchoise = jogadores.get(position);
        boolean photo = jchoise.getPhotoUrl() != null;
        if(photo){
            jholder.photoImageView.setVisibility(View.VISIBLE);
            //Glide.with(jholder.photoImageView.getContext())
            //        .load(jchoise.getPhotoUrl())
            //        .into(jholder.photoImageView);
            if(jchoise.getPhotoUrl().isEmpty()){
                String url = "https://raw.githubusercontent.com/google/material-design-icons/master/action/2x_web/ic_android_black_48dp.png";
                GlideUtil.loadProfileIcon(url,jholder.photoImageView);
            }
            else{
                GlideUtil.loadProfileIcon(jchoise.getPhotoUrl(),jholder.photoImageView);
            }

        }else{
            jholder.photoImageView.setVisibility(View.GONE);
        }
        jholder.emailTextView.setText(jchoise.getEmail());
        jholder.nomeTextView.setText(jchoise.getNome());
        jholder.posicaoTextView.setText(jchoise.getPosicao());
        if(jchoise.getPe_melhor().toLowerCase().equals("direito")){
            jholder.peTextView.setText("destro");
        }else if(jchoise.getPe_melhor().toLowerCase().equals("esquerdo")){
            jholder.peTextView.setText("canhoto");
        }else{
            jholder.peTextView.setText("ambidestro");
        }
        String svalue = Formatador.VALOR_COM_SIMBOLO.formata(String.valueOf(jchoise.getPretencao_salarial()));
        String cvalue = Formatador.VALOR_COM_SIMBOLO.formata(String.valueOf(jchoise.getPretencao_contratual()));
        Log.i("formatado",svalue);
        jholder.psTextView.setText(svalue);
        jholder.pcTextView.setText(cvalue);
        //frutaholder.textViewNome.setText(frutaescolhida.getNome());
        //frutaholder.img.setImageResource(frutaescolhida.getImg());
    }

    @Override
    public int getItemCount() {
        return jogadores == null ? 0 : jogadores.size();
    }
}
