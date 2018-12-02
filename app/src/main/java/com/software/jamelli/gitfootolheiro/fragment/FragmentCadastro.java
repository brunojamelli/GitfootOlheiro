package com.software.jamelli.gitfootolheiro.fragment;

import android.os.Bundle;


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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.software.jamelli.gitfootolheiro.R;
import com.software.jamelli.gitfootolheiro.modelo.Jogador;
import com.software.jamelli.gitfootolheiro.modelo.Olheiro;
import com.software.jamelli.gitfootolheiro.util.FirebaseUtil;

import java.util.UUID;

public class FragmentCadastro extends Fragment implements View.OnClickListener{
    private Button btn_cad;
    private FirebaseDatabase fdatabase;
    private DatabaseReference dataref;
    private ChildEventListener clistener;
    private EditText et_time,et_emp,et_gp;
    private LinearLayout tela;

    public FragmentCadastro() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b) {
        View v = inflater.inflate(R.layout.fragment_cadastro, container, false);
        btn_cad = v.findViewById(R.id.btnCadOlheiro);
        initViewObjects(v);
        initDBandAuth();
        return v;
    }

    private void initViewObjects(View v){
        et_time = v.findViewById(R.id.etTime);
        et_emp = v.findViewById(R.id.etEmp);
        et_gp = v.findViewById(R.id.etGrupo);
        tela = v.findViewById(R.id.telaCad);
        btn_cad = v.findViewById(R.id.btnCadOlheiro);
        btn_cad.setOnClickListener(this);
    }

    private void initDBandAuth(){
        fdatabase = FirebaseDatabase.getInstance();
        dataref = fdatabase.getReference().child("olheiro");;
    }

    public void clearFields(){
        et_gp.setText("");
        et_emp.setText("");
        et_time.setText("");
    }

    @Override
    public void onClick(final View v) { //check for what button is pressed
        String texto="",time="",empresa="",grupo="";
        String photoUrl = FirebaseUtil.getOlheiro().getPhotoUrl();
        switch (v.getId()) {
            case R.id.btnCadOlheiro:
                if(et_time.getText().toString().equals("") || et_time.getText().toString().equals("") ||
                        et_time.getText().toString().equals("")){
                    texto = "Olheiro não pode ser cadastrado sem nenhuma informação";
                }else{
                    if(FirebaseUtil.getJogador().getNome() == null){
                        Log.i("seila1","Provider de id"+FirebaseUtil.getCurrentUserId());
                    }else {
                        if(photoUrl == null){
                            photoUrl = "";
                        }else{
                            if(!et_time.getText().toString().equals("")){
                                time = et_time.getText().toString();
                            }
                            if(!et_gp.getText().toString().equals("")) {
                                grupo = et_gp.getText().toString();
                            }
                            if(!et_gp.getText().toString().equals("")){
                                empresa = et_emp.getText().toString();
                            }
                            Log.i("seila1","Provider de id"+FirebaseUtil.getCurrentUserId());
                            Log.i("seila2", FirebaseUtil.getOlheiro().getNome());
                            Log.i("seila3",FirebaseUtil.getOlheiro().getEmail());

                            //instanciar olheiro e colocar ele no firebase
                            Olheiro ol = new Olheiro(
                                    //UUID.randomUUID().toString(),
                                    FirebaseUtil.getCurrentUserId(),
                                    FirebaseUtil.getOlheiro().getPhotoUrl(),
                                    FirebaseUtil.getOlheiro().getEmail(),
                                    FirebaseUtil.getOlheiro().getNome(),
                                    time,empresa,grupo
                            );

                            dataref.child(ol.getUid()).setValue(ol);
                            clearFields();
                            texto = "Cadastro realizado com sucesso";
                        }
                    }



                }

                Snackbar.make(tela, texto, Snackbar.LENGTH_LONG).show();

                break;

        }

    }
}
