package com.software.jamelli.gitfootolheiro.util;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.software.jamelli.gitfootolheiro.modelo.Jogador;
import com.software.jamelli.gitfootolheiro.modelo.Olheiro;

public class FirebaseUtil {
    public static DatabaseReference getBaseRef() {
        return FirebaseDatabase.getInstance().getReference();
    }

    public static DatabaseReference getBaseRefJogador() {
        return FirebaseDatabase.getInstance().getReference().child("jogador");
    }

    public static DatabaseReference getBaseRefOlheiro() {
        return FirebaseDatabase.getInstance().getReference().child("olheiro");
    }

    public static String getCurrentUserId() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            return user.getUid();
        }
        return null;
    }

    public static Jogador getJogador() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) return null;
        return new Jogador(user.getPhotoUrl().toString(), user.getEmail(), user.getDisplayName());
    }

    public static Olheiro getOlheiro() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) return null;
        return new Olheiro(user.getPhotoUrl().toString(), user.getEmail(), user.getDisplayName());
    }

    public static DatabaseReference getJogadorAtualRef() {
        String uid = getCurrentUserId();
        if (uid != null) {
            return getBaseRef().child("jogador").child(getCurrentUserId());
        }
        return null;
    }

    public static DatabaseReference getOlheiroAtualRef() {
        String uid = getCurrentUserId();
        if (uid != null) {
            return getBaseRef().child("olheiro").child(getCurrentUserId());
        }
        return null;
    }

    public static DatabaseReference getJogadoresRef() {
        return getBaseRef().child("jogador");
    }

    public static DatabaseReference getOlheirosRef() {
        return getBaseRef().child("olheiro");
    }

}