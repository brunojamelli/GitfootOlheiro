package com.software.jamelli.gitfootolheiro.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.software.jamelli.gitfootolheiro.R;
import com.software.jamelli.gitfootolheiro.modelo.Jogador;
import com.software.jamelli.gitfootolheiro.modelo.Localization;
import com.software.jamelli.gitfootolheiro.recycler.JogadorAdapter;
import com.software.jamelli.gitfootolheiro.util.FirebaseUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FragmentMapeamento extends Fragment{
    private ChildEventListener clistener;
    private FirebaseDatabase fdatabase;
    private DatabaseReference dataref;
    private ArrayList<Jogador> mUsersDataset;
    private List<LatLng> locals;
    public static String TAG = "lista";
    public FragmentMapeamento(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b) {
        View v = inflater.inflate(R.layout.fragment_mapeamento, container, false);
        initDBandAuth();
        dataref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long value=dataSnapshot.getChildrenCount();
                Jogador post = dataSnapshot.getValue(Jogador.class);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return v;
    }

    public void initDBandAuth(){

        dataref = FirebaseUtil.getBaseRefJogador();
    }



}
