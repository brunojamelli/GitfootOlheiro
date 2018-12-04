package com.software.jamelli.gitfootolheiro.fragment;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.software.jamelli.gitfootolheiro.R;
import com.software.jamelli.gitfootolheiro.modelo.Olheiro;
import com.software.jamelli.gitfootolheiro.util.FirebaseUtil;
import com.software.jamelli.gitfootolheiro.util.GlideUtil;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentInicio extends Fragment{
    private TextView txInicio;

    private FirebaseDatabase fdatabase;
    private DatabaseReference dataref;
    public FragmentInicio() {
    }
    public void initDB(){
        fdatabase = FirebaseDatabase.getInstance();
        dataref = fdatabase.getReference().child("olheiro");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b) {
        View v = inflater.inflate(R.layout.fragment_inicio, container, false);
        return v;
    }
}
