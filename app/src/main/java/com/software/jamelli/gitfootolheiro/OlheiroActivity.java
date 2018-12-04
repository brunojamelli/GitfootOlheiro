package com.software.jamelli.gitfootolheiro;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseUser;
import com.software.jamelli.gitfootolheiro.fragment.*;
import com.software.jamelli.gitfootolheiro.util.FirebaseUtil;
import com.software.jamelli.gitfootolheiro.util.GlideUtil;

import de.hdodenhof.circleimageview.CircleImageView;

public class OlheiroActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private NavigationView navigationView;
    TextView txtNome;
    TextView txtEmail;
    CircleImageView photo;
    String photoUrl,nome,email;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olheiro);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Gitfoot Olheiro");
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);

        //codigo para colocar os dados do usuario no navegation drawer
        txtNome = navigationView.getHeaderView(0).findViewById(R.id.tv_bv2);
        photo = navigationView.getHeaderView(0).findViewById(R.id.profile_image2);
        try{
            nome = FirebaseUtil.getJogador().getNome();
            photoUrl = FirebaseUtil.getJogador().getPhotoUrl();
            txtNome.setText("Seja bem vindo\n"+nome);
            GlideUtil.loadProfileIcon(photoUrl,photo);
        }catch (NullPointerException e){
            Log.i("deu bode",e.toString());
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, new FragmentInicio()).commit();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int idItem = menuItem.getItemId();
                Fragment frags = null;
                switch (idItem){
                    case R.id.nav_home_fragment:
                        frags = new FragmentInicio();
                        drawer.closeDrawer(GravityCompat.START);
                        toolbar.setTitle("Inicio");
                        break;
                    case R.id.nav_cadastro_fragment:
                        toolbar.setTitle("Adicionar Informações");
                        frags = new FragmentCadastro();
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_localizacao_fragment:
                        toolbar.setTitle("Salvar Localização");
                        frags = new FragmentLocalizacao();
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_mostrar_fragment:
                        toolbar.setTitle("Mostrando Jogadores");
                        frags = new FragmentMostrar();
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_mapear_fragment:
                        toolbar.setTitle("Mapeando os Jogadores");
                        frags = new FragmentMapeamento();
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_about_fragment:
                        toolbar.setTitle("Sobre");
                        frags = new FragmentSobre();
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_sair:
                        drawer.closeDrawer(GravityCompat.START);
                        AuthUI.getInstance().signOut(OlheiroActivity.this);
                        break;
                }
                if(idItem != R.id.nav_sair){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, frags).commit();
                }

                return true;
            }
        });
    }

    @Override
    public void onBackPressed() { }
}
