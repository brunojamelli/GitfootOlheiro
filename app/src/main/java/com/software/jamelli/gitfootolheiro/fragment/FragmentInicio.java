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
    private CircleImageView profileFoto;
    private String nome;
    private String photoUrl;
    private FirebaseDatabase fdatabase;
    private DatabaseReference dataref;
    public static String UID="";
    public FragmentInicio() {
    }
    public void initDB(){
        fdatabase = FirebaseDatabase.getInstance();
        dataref = fdatabase.getReference().child("olheiro");
    }
    public void seachKey(String email){
        Query query1 = dataref.child("email").equalTo(email).limitToFirst(1);
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UID = dataSnapshot.getValue(Olheiro.class).getUid();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b) {
        View v = inflater.inflate(R.layout.fragment_inicio, container, false);
        txInicio = v.findViewById(R.id.tv_bv);
        profileFoto = v.findViewById(R.id.profile_image);
        photoUrl = FirebaseUtil.getOlheiro().getPhotoUrl();

        nome = FirebaseUtil.getOlheiro().getNome();
        txInicio.setText(getString(R.string.bem_vindo) +" "+nome);
        GlideUtil.loadProfileIcon(photoUrl,profileFoto);
        initDB();
        UID = FirebaseUtil.getCurrentUserId();
        //seachKey(FirebaseUtil.getOlheiro().getEmail());
        Log.i("meuid",UID);
        return v;
    }
}
