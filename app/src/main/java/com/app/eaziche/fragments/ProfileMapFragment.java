package com.app.eaziche.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.eaziche.R;
import com.app.eaziche.models.ShopDetails;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ProfileMapFragment extends Fragment implements OnMapReadyCallback{

    GoogleMap mMap;
    ShopDetails shopDetails;

    public ProfileMapFragment() {
        // Required empty public constructor
    }
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view ==null) {
            view = inflater.inflate(R.layout.tab_maps, container, false);
            shopDetails = new ShopDetails();
            return view;
        }else
            return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);

    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getCatId(final ShopDetails details) {
       shopDetails = details;
        shopDetails.setLat(details.getLat());
        shopDetails.setLon(details.getLon());

    }
        @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
GIT
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(Double.parseDouble(shopDetails.getLat()), Double.parseDouble(shopDetails.getLon()));
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Porbandar"));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15.0f));

    }
}
