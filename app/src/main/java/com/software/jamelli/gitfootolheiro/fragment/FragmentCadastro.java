package com.software.jamelli.gitfootolheiro.fragment;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.software.jamelli.gitfootolheiro.modelo.Jogador;

import java.util.UUID;
import com.software.jamelli.gitfootolheiro.R;
import com.software.jamelli.gitfootolheiro.util.FirebaseUtil;

public class FragmentCadastro extends Fragment implements View.OnClickListener{
    private Button btn_cad;
    private FirebaseDatabase fdatabase;
    private DatabaseReference dataref;
    private ChildEventListener clistener;
    private EditText et_ps,et_pc;
    private Spinner sp_pe,sp_pos;
    private LinearLayout tela;
    public static Jogador jogSearch;
    private static final String[] CLUBES = new String[]{"Flamengo",  "Palmeiras", "Vasco", "Botafogo", "Cruzeiro", "Avaí", "América-MG", "Atlético-PR"};
    private static final String[] POSICOES = new String[]
            {"Goleiro", "Zagueiro", "Lateral Direito", "Lateral Esquerdo",
                    "Volante", "Meia Armador", "Segundo Atacante","Centro Avante"};
    private static final String[] PE_MELHOR = new String[]
            {"Direito","Esquerdo","Ambos"};
    public FragmentCadastro() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b) {
        View v = inflater.inflate(R.layout.fragment_cadastro, container, false);
        btn_cad = v.findViewById(R.id.btnCadJogador);
        initViewObjects(v);
        initDBandAuth();
        return v;
    }

    private void initViewObjects(View v){
        //et_pe = v.findViewById(R.id.etPe);
        //et_pisicao = v.findViewById(R.id.etPos);
        et_ps = v.findViewById(R.id.etPS);
        et_pc = v.findViewById(R.id.etPC);
        tela = v.findViewById(R.id.telaCad);
        btn_cad = v.findViewById(R.id.btnCadJogador);
        btn_cad.setOnClickListener(this);
        sp_pe = v.findViewById(R.id.spPe);
        sp_pos = v.findViewById(R.id.spPos);

        ArrayAdapter adp1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, PE_MELHOR);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sp_pe.setAdapter(adp1);

        ArrayAdapter adp2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, POSICOES);
        adp2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sp_pos.setAdapter(adp2);


    }

    private void initDBandAuth(){
        dataref = FirebaseUtil.getBaseRefJogador();
    }

    public void clearFields(){
        sp_pe.setSelection(1);
        sp_pos.setSelection(1);
        et_ps.setText("");
        et_pc.setText("");
    }

    public boolean checkExistingPlayer(String email){
        /*clistener = new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.i("achou","achou -> "+dataSnapshot.toString());
                FragmentCadastro.jogSearch =  dataSnapshot.getValue(Jogador.class);
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
        dataref.orderByChild("email").equalTo(email).addChildEventListener(clistener);

        if(FragmentCadastro.jogSearch!= null){
            return true;
        }*/
        Query query1 = dataref.orderByChild("email").equalTo(email).limitToFirst(1);
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("achou","achou -> "+dataSnapshot.toString());
                FragmentCadastro.jogSearch =  dataSnapshot.getValue(Jogador.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        if(jogSearch != null){
            return true;
        }
        return false;

    }
    @Override
    public void onClick(final View v) { //check for what button is pressed
        String texto="";
        Log.i("escolhido",sp_pe.getSelectedItem().toString());
        switch (v.getId()) {
            case R.id.btnCadJogador:
                if(checkExistingPlayer(FirebaseUtil.getJogador().getEmail())){
                    texto = "Desculpe, já existe jogador com esse email";
                }else{
                    Log.i("cad1",FirebaseUtil.getJogador().getEmail());
                    Jogador j = new Jogador(
                            UUID.randomUUID().toString(),
                            FirebaseUtil.getJogador().getPhotoUrl(),
                            FirebaseUtil.getJogador().getEmail(),
                            FirebaseUtil.getJogador().getNome(),
                            //et_pe.getText().toString(),
                            sp_pe.getSelectedItem().toString(),
                            sp_pos.getSelectedItem().toString(),
                            //et_pisicao.getText().toString(),
                            Double.parseDouble(et_ps.getText().toString()),
                            Double.parseDouble(et_pc.getText().toString())
                    );
                    dataref.child(j.getUid()).setValue(j);
                    clearFields();
                    texto = "Cadastro realizado com sucesso";
                    }
                Snackbar.make(tela, texto, Snackbar.LENGTH_LONG).show();

                break;

        }

    }
}
