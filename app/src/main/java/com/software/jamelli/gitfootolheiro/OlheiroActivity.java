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
import android.view.MenuItem;
import android.widget.ImageView;

import com.firebase.ui.auth.AuthUI;
import com.software.jamelli.gitfootolheiro.fragment.*;

public class OlheiroActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ImageView im_logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olheiro);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        //im_logo = findViewById(R.id.logo_menu);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
//        GlideUtil.loadProfileIcon(FirebaseUtil.getOlheiro().getPhotoUrl(),im_logo);
        toolbar.setTitle("Gitfoot");
        navigationView = findViewById(R.id.nav_view);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, new FragmentInicio()).commit();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int idItem = menuItem.getItemId();
                Fragment frags = null;
                switch (idItem){
                    case R.id.nav_first_fragment:
                        frags = new FragmentInicio();
                        drawer.closeDrawer(GravityCompat.START);
                        toolbar.setTitle("Inicio");
                        break;
                    case R.id.nav_second_fragment:
                        toolbar.setTitle("Adicionar Informações");
                        frags = new FragmentCadastro();
                        drawer.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_third_fragment:
                        toolbar.setTitle("Mostrando Jogadores");
                        frags = new FragmentMostrar();
                        drawer.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_fourth_fragment:
                        toolbar.setTitle("Adicione sua localização");
                        frags = new FragmentLocalizacao();
                        drawer.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_fifth_fragment:
                        toolbar.setTitle("Mapeando os Jogadores");
                        frags = new FragmentMapeamento();
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
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
