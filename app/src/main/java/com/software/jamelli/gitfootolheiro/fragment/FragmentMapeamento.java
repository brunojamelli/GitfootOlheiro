package com.software.jamelli.gitfootolheiro.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.maps.android.heatmaps.HeatmapTileProvider;
import com.software.jamelli.gitfootolheiro.R;
import com.software.jamelli.gitfootolheiro.modelo.Jogador;
import com.software.jamelli.gitfootolheiro.modelo.Localization;
import com.software.jamelli.gitfootolheiro.recycler.JogadorAdapter;
import com.software.jamelli.gitfootolheiro.util.FirebaseUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FragmentMapeamento extends Fragment implements OnMapReadyCallback {
    private ChildEventListener clistener;
    private FirebaseDatabase fdatabase;
    private DatabaseReference dataref;
    private ArrayList<Jogador> mUsersDataset;
    public static List<LatLng> locals = new ArrayList<>();
    private final int CODE_HEAT_MAP = 59;

    public static String TAG = "lista";
    HeatmapTileProvider mProvider;
    TileOverlay mOverlay;
    private GoogleMap mMap;
    public FragmentMapeamento(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b) {
        Log.i("criou", "entruo no on create");
        View v = inflater.inflate(R.layout.fragment_mapeamento, container, false);
        initDBandAuth();

        dataref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long value=dataSnapshot.getChildrenCount();
                for (DataSnapshot snap: dataSnapshot.getChildren()) {
                    if(snap.child("localization").getValue(Localization.class) == null){
                        Log.i(TAG, "sem localização");
                    }else{
                        Log.i(TAG, String.valueOf(snap.child("localization").getValue(Localization.class)));
                        double lat = snap.child("localization").getValue(Localization.class).getLatitude();
                        double lng = snap.child("localization").getValue(Localization.class).getLongitude();
                        FragmentMapeamento.locals.add(new LatLng(lat,lng));
                        Log.i("lat", String.valueOf(lat));
                    }
                }
                Log.d("lista1", locals.toString());
                addHeatMap(FragmentMapeamento.locals);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.mapa2);
        mapFragment.getMapAsync(this);

        return v;
    }

    public void initDBandAuth(){

        dataref = FirebaseUtil.getBaseRefJogador();
    }

    public void addHeatMap(List<LatLng> pontos){
        Log.d("lista2", pontos.toString());
        mProvider = new HeatmapTileProvider.Builder()
                .data(locals)
                .build();
        // Add a tile overlay to the map, using the heat map tile provider.
        mOverlay = mMap.addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            // Show rationale and request permission.
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, CODE_HEAT_MAP);
        }
    }
}
