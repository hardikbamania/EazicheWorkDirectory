package com.app.eaziche.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
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
import com.app.eaziche.utils.Mysingleton;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.HashMap;
import java.util.Map;

import static com.app.eaziche.utils.Config.PHONE;
import static com.app.eaziche.utils.Config.REGISTED;
import static com.app.eaziche.utils.Config.USERNAME;

public class VerificationActivity extends Activity implements OnClickListener {

    EditText eName, eNumber;
    Button bContinue;
    String name, number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        eName = (EditText) findViewById(R.id.etName);
        eNumber = (EditText) findViewById(R.id.etMobile);
        bContinue = (Button) findViewById(R.id.bContinue);

        bContinue.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        register();
    }

    private void register() {
        final ProgressDialog loading = ProgressDialog.show(this, "Registering", "Please wait...", false, false);

        if(eNumber.getText().length()!=10) {
            Toast.makeText(VerificationActivity.this, "Please ! Enter Valid Mobile No..!", Toast.LENGTH_LONG).show();
            loading.dismiss();
            return;
        }
        name = eName.getText().toString().trim();
        number = eNumber.getText().toString().trim();

        StringRequest request = new StringRequest(Request.Method.POST, Config.REGISTER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                try {
                    response = response.trim();
                    Log.d("ASSETS response", response);
                    //If it is success
                    if (response.equals("Status=0")) {
                        REGISTED = true;
                        USERNAME = name;
                        PHONE = number;
                        Toast.makeText(VerificationActivity.this, "User Successfully Registered", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(VerificationActivity.this, ConfirmActivity.class);
                        intent.putExtra("username", name);
                        intent.putExtra("phone", number);
                        startActivity(intent);

                    } else {
                        //If not successful user may already have registered
                        Toast.makeText(VerificationActivity.this, "Username or Phone number already registered", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(VerificationActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding the parameters to the request
                params.put(Config.KEY_USERNAME, name);
                params.put(Config.KEY_PHONE, number);
                return params;
            }
        };
        Mysingleton.getInstance(this).addToRequestQue(request);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
