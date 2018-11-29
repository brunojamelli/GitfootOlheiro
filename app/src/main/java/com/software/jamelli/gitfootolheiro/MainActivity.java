package com.software.jamelli.gitfootolheiro;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.software.jamelli.gitfootolheiro.util.FirebaseUtil;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String username;
    private FirebaseAuth fauth;
    private FirebaseAuth.AuthStateListener authStateListener;
    public static final String ANONYMOUS = "anonymous";
    private static final int LOGIN_CODE = 11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fauth = FirebaseAuth.getInstance();
        username = ANONYMOUS;
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    //logado
                    Intent i = new Intent(getApplicationContext(),OlheiroActivity.class);
                    startActivity(i);
                }else{
                    //não-logado
                    //chama o fluxo de login
                    List<AuthUI.IdpConfig> providers = Arrays.asList(
                            new AuthUI.IdpConfig.EmailBuilder().build(),
                            new AuthUI.IdpConfig.GoogleBuilder().build());

                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(providers)
                                    .build(),
                            LOGIN_CODE);
                }
            }
        };
        fauth.addAuthStateListener(authStateListener);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Bem-vindo "+ FirebaseUtil.getOlheiro().getNome(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),OlheiroActivity.class);
                startActivity(i);
            } else if (resultCode == RESULT_CANCELED) {
                //finish();

            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
