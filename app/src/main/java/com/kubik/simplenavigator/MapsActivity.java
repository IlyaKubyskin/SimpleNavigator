package com.kubik.simplenavigator;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;
import com.kubik.simplenavigator.pojo.Direction;
import com.kubik.simplenavigator.pojo.Route;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, OnDirectionFoundListener {

    private GoogleMap mMap;

    Button findPath;
    EditText origin;
    EditText destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        findPath = findViewById(R.id.btnFindPath);
        origin = findViewById(R.id.etOrigin);
        destination = findViewById(R.id.etDestination);

        findPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });
    }

    private void sendRequest() {

        String orig = String.valueOf(origin.getText());
        String dest = String.valueOf(destination.getText());

        DirectionFinder finder = new DirectionFinder(orig, dest, this);
        finder.findDeWei();
        List<Route> routes = finder.getRoutes();

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
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        mMap.setMyLocationEnabled(true);
    }

    @Override
    public void onDirectionFinderStart() {
        Toast.makeText(this, "startSearch", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDirectionFinderSuccess(Direction direction) {
        PolylineOptions options = new PolylineOptions()
                .geodesic(true)
                .color(Color.BLUE)
                .width(10);


        List<LatLng> list = PolyUtil.decode(direction.getRoutes().get(0).getOverviewPolyline().getPoints());
        options.addAll(list);
        mMap.addPolyline(options);
    }
}
