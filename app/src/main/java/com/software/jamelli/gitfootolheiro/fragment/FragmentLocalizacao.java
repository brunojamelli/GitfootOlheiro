package com.software.jamelli.gitfootolheiro.fragment;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.software.jamelli.gitfootolheiro.R;
import com.software.jamelli.gitfootolheiro.util.FirebaseUtil;


public class FragmentLocalizacao extends Fragment implements View.OnClickListener,OnMapReadyCallback,
        GoogleMap.OnMyLocationClickListener,
        GoogleMap.OnMyLocationButtonClickListener{
    private Button btn_loc;
    private FirebaseDatabase fdatabase;
    private DatabaseReference dataref;
    private GoogleMap mMap;
    private final int CODE_LOCATION = 58;
    private FusedLocationProviderClient locationClient;
    private Location myLocation;

    public FragmentLocalizacao(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b) {
        View v = inflater.inflate(R.layout.fragment_localizar, container, false);
        btn_loc = v.findViewById(R.id.btnLocalizar);
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);
        locationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        btn_loc.setOnClickListener(this);
        initDBandAuth();
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLocalizar:
                double lat = myLocation.getLatitude();
                double longt = myLocation.getLongitude();
                dataref.child(FirebaseUtil.getCurrentUserId()).child("localization").child("latitude").setValue(lat);
                dataref.child(FirebaseUtil.getCurrentUserId()).child("localization").child("longitude").setValue(longt);
                Toast.makeText(getActivity(),"Localização salva",Toast.LENGTH_SHORT).show();
                Log.i("local",myLocation.toString());
                break;
        }
    }

    private void initDBandAuth(){
        fdatabase = FirebaseDatabase.getInstance();
        dataref = fdatabase.getReference().child("olheiro");;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            mMap.setOnMyLocationButtonClickListener(this);
            mMap.setOnMyLocationClickListener(this);
        } else {
            // Show rationale and request permission.
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, CODE_LOCATION);
        }

        locationClient.getLastLocation()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            Log.i("opa",location.toString());
                            myLocation = location;
                        }else{

                        }
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == CODE_LOCATION) {
            if (permissions.length == 1 &&
                    permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    mMap.setMyLocationEnabled(true);
                }else{
                    LatLng cr = new LatLng(-6.24345, -36.1805);
                    mMap.addMarker(new MarkerOptions().position(cr).title("Marcado em Campo Redondo"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(cr));
                }
            }

        } else {
            // Permission was denied. Display an error message.
            Toast.makeText(getContext(), "Permissão não concedida, posição padrão marcada no mapa", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        String txtLocation = "Localização atual : [" +location.getLongitude()+","+location.getLatitude()+"]";
        Log.i("",txtLocation);
        Toast.makeText(getActivity(), txtLocation, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(getActivity(), "Aproximando a sua localização no mapa", Toast.LENGTH_SHORT).show();
        return false;
    }
}
