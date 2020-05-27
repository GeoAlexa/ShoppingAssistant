package com.example.shoppingassistant.map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.example.shoppingassistant.R;
import com.example.shoppingassistant.main.generic.BaseScreen;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import java.util.Objects;

public class MapScreen extends BaseScreen implements OnMapReadyCallback, LocationListener {

    SupportMapFragment mapFragment;
    GoogleMap map;
    LocationManager locationManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.map_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mapFragment = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_frag));
        mapFragment.getMapAsync(this);

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    @SuppressLint("MissingPermission")
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMapType(1);
        map.setMyLocationEnabled(true);

        if (!hasPermission()) {
            return;
        }

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 100, this);
    }

    private boolean hasPermission() {
        return ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .zoom(17)
                    .target(new LatLng(location.getLatitude(), location.getLongitude()))
                    .bearing(90)
                    .tilt(40)
                    .build();
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
