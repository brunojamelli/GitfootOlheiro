package com.software.jamelli.gitfootolheiro.fragment;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.software.jamelli.gitfootolheiro.modelo.Jogador;
import com.software.jamelli.gitfootolheiro.recycler.JogadorAdapter;

import java.util.ArrayList;
import java.util.List;
import com.software.jamelli.gitfootolheiro.R;

public class FragmentMostrar extends Fragment{
    private List<Jogador> jogadores;
    private RecyclerView rv;
    private JogadorAdapter adapter;
    private ChildEventListener clistener;
    private FirebaseDatabase fdatabase;
    private DatabaseReference dataref;
    private Spinner sp_ord;
    private static final String[] ORDENADORES = new String[]
            {"nome","pe_melhor","posicao"};
    public FragmentMostrar() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b) {
        View v = inflater.inflate(R.layout.fragment_mostrar, container, false);
        initDBandAuth();
        initViewObjects(v);
        readDatabase();
        //readDatabaseClassified(sp_ord.getSelectedItem().toString());
        return v;
    }
    public void initViewObjects(View v){
        rv = v.findViewById(R.id.screenPlayers);
        sp_ord = v.findViewById(R.id.spOrd);
        ArrayAdapter adp = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, ORDENADORES);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sp_ord.setAdapter(adp);
        jogadores = new ArrayList<>();
        adapter = new JogadorAdapter(getContext(),jogadores);
        rv.setAdapter(adapter);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        //((LinearLayoutManager) layout).setStackFromEnd(true);
        //((LinearLayoutManager) layout).setReverseLayout(false);
        rv.setLayoutManager(layout);
    }
    public void initDBandAuth(){
        fdatabase = FirebaseDatabase.getInstance();
        dataref = fdatabase.getReference().child("jogador");
    }
    public void readDatabase(){
        clistener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Jogador j = dataSnapshot.getValue(Jogador.class);
                jogadores.add(j);
                adapter = new JogadorAdapter(getContext(),jogadores);
                rv.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        };
        dataref.addChildEventListener(clistener);
    }

    private void removeReadListener(){
        if(clistener != null) {
            dataref.removeEventListener(clistener);
            clistener = null;
        }
    }

    public void readDatabaseClassified(String classified){
        if(classified == null){
            classified = "nome";
        }
        Query read = dataref.child("jogador").orderByChild(classified);
        removeReadListener();
        read.addChildEventListener(clistener);
    }
}
