package com.app.eaziche.activities;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.app.eaziche.R;
import com.app.eaziche.utils.Config;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.HashMap;
import java.util.Map;

import static com.app.eaziche.utils.Config.PHONE;
import static com.app.eaziche.utils.Config.USERNAME;

public class ConfirmActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    GoogleMap mMap;
    Button bConfirm;
    EditText eConfirm;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        eConfirm = (EditText) findViewById(R.id.etConfirm);
        bConfirm = (Button) findViewById(R.id.bConfirm);
        bConfirm.setOnClickListener(this);
        queue = Volley.newRequestQueue(ConfirmActivity.this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

    }

    //On the click of the confirm button from alert dialog
    @Override
    public void onClick(View v) {
        //Hiding the alert dialog

        //Displaying a progressbar
        final ProgressDialog loading = ProgressDialog.show(ConfirmActivity.this, "Authenticating", "Please wait while we check the entered code", false, false);

        //Getting the user entered otp from edittext
        final String otp = eConfirm.getText().toString().trim();
        try {
            //Creating an string request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.CONFIRM_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //if the server response is success
                            try {
                                loading.dismiss();
                                response = response.trim();
                                Log.d("RESPONCE", response);
                                if (response.equalsIgnoreCase("success")) {
                                    //Starting a new activity
                                    Config.VERIFIED = true;
                                    startActivity(new Intent(ConfirmActivity.this, MainActivity.class));

                                } else {
                                    //Displaying a toast if the otp entered is wrong
                                    Toast.makeText(ConfirmActivity.this, "Wrong OTP Please Try Again", Toast.LENGTH_LONG).show();
                                }
                            } catch (Exception e) {

                                Toast.makeText(ConfirmActivity.this, "Something went Wrong :" + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            loading.dismiss();
                            Toast.makeText(ConfirmActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    //Adding the parameters otp and username
                    params.put(Config.KEY_OTP, otp);
                    params.put(Config.KEY_USERNAME, USERNAME);
                    params.put(Config.KEY_PHONE, PHONE);
                    return params;
                }
            };

            //Adding the request to the queue
            queue.add(stringRequest);
        } catch (Exception e) {
            Toast.makeText(ConfirmActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.d("message", e.getMessage());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(21.6417, 69.6293);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Porbandar"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15));
    }
}
