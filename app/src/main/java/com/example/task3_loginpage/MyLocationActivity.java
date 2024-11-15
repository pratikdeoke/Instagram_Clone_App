package com.example.task3_loginpage;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.task3_loginpage.databinding.ActivityMyLocationBinding;
import com.google.android.gms.maps.model.PolylineOptions;

public class MyLocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMyLocationBinding binding;

    Button btn_my_location_satellite, btn_my_location_terrain, btn_my_location_hybrid, btn_my_location_normal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMyLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btn_my_location_satellite = findViewById(R.id.btn_my_location_satellite);
        btn_my_location_terrain = findViewById(R.id.btn_my_location_terrain);
        btn_my_location_hybrid = findViewById(R.id.btn_my_location_hybrid);
        btn_my_location_normal = findViewById(R.id.btn_my_location_normal);


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng myLocation = new LatLng(20.945909552949484, 77.76396128265382);
        mMap.addMarker(new MarkerOptions().position(myLocation).title("Govt. Polytechnic Amravati"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 16), 5000, null);

        mMap.addCircle(new CircleOptions()
                .center(myLocation)
                        .radius(150.15)
                .strokeColor(Color.parseColor("#06E3FF"))
                .fillColor(Color.parseColor("#06E3FF"))


                );

        btn_my_location_satellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        });

        btn_my_location_terrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            }
        });

        btn_my_location_hybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });

        btn_my_location_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });

        LatLng destination = new LatLng(21.403307160824507, 77.32670720035253);
        mMap.addMarker(new MarkerOptions().position(destination).title("Chikhaldara"));

        mMap.addPolyline(new PolylineOptions()
                .add(myLocation, destination)
                .width(2)
                .color(Color.RED)
                .geodesic(true)

        );

    }
}