package com.software.jamelli.gitfootolheiro.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jamelli.gitfootjogador.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class JogadorViewHolder extends RecyclerView.ViewHolder{
    //final ImageView photoImageView;
    final CircleImageView photoImageView;
    final TextView emailTextView;
    final TextView nomeTextView;
    final TextView peTextView;
    final TextView posicaoTextView;
    final TextView psTextView;
    final TextView pcTextView;

    public JogadorViewHolder(View v){
        super(v);
        photoImageView = v.findViewById(R.id.photoImageView);
        emailTextView = v.findViewById(R.id.emailTextView);
        nomeTextView = v.findViewById(R.id.nomeTextView);
        peTextView = v.findViewById(R.id.peTextView);
        posicaoTextView = v.findViewById(R.id.posicaoTextView);
        psTextView = v.findViewById(R.id.psTextView);
        pcTextView = v.findViewById(R.id.pcTextView);
    }
}
