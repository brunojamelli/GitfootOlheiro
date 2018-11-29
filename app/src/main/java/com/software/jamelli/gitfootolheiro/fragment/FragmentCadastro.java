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
        btn_cad = v.findViewById(R.id.btnCadJogador);
        initViewObjects(v);
        initDBandAuth();
        return v;
    }

    private void initViewObjects(View v){
        //et_pe = v.findViewById(R.id.etPe);
        //et_pisicao = v.findViewById(R.id.etPos);
        et_time = v.findViewById(R.id.etTime);
        et_emp = v.findViewById(R.id.etEmp);
        et_gp = v.findViewById(R.id.etGrupo);
        tela = v.findViewById(R.id.telaCad);
        btn_cad = v.findViewById(R.id.btnCadOlheiro);
        btn_cad.setOnClickListener(this);
        //sp_pe = v.findViewById(R.id.spPe);
        //sp_pos = v.findViewById(R.id.spPos);

        /*
        ArrayAdapter adp1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, PE_MELHOR);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sp_pe.setAdapter(adp1);

        ArrayAdapter adp2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, POSICOES);
        adp2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sp_pos.setAdapter(adp2);
        */

    }

    private void initDBandAuth(){
        fdatabase = FirebaseDatabase.getInstance();
        dataref = fdatabase.getReference().child("jogador");;
    }

    public void clearFields(){
        /*sp_pe.setSelection(1);
        sp_pos.setSelection(1);
        et_ps.setText("");
        et_pc.setText("");*/
        et_gp.setText("");
        et_emp.setText("");
        et_time.setText("");
    }

    @Override
    public void onClick(final View v) { //check for what button is pressed
        String texto="",time="",empresa="",grupo="";

        switch (v.getId()) {
            case R.id.btnCadOlheiro:
                if(et_time.getText().toString().equals("") || et_time.getText().toString().equals("") ||
                        et_time.getText().toString().equals("")){
                    texto = "Olheiro não pode ser cadastrado sem nenhuma informação";
                }else{
                    if(!et_time.getText().toString().equals("")){
                        time = et_time.getText().toString();
                    }
                    else if(!et_gp.getText().toString().equals("")) {
                        grupo = et_gp.getText().toString();
                    }
                    else if(!et_gp.getText().toString().equals("")){
                        empresa = et_emp.getText().toString();
                    }
                    Log.i("cadid",FirebaseUtil.getCurrentUserId());
                    //instanciar olheiro e colocar ele no firebase
                    /*Jogador j = new Jogador(
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
                    clearFields();*/
                    texto = "Cadastro realizado com sucesso";
                }

                Snackbar.make(tela, texto, Snackbar.LENGTH_LONG).show();

                break;

        }

    }
}
