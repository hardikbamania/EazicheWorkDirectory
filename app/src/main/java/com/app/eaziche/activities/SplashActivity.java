package com.app.eaziche.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.app.eaziche.R;
import com.app.eaziche.utils.Config;


public class SplashActivity extends AppCompatActivity implements Runnable{

    Thread t ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        t = new Thread(this);
        t.start();

    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            Intent i;
            if(!Config.VERIFIED && !Config.REGISTED)
            i  = new Intent(SplashActivity.this,VerificationActivity.class);
            else if(!Config.VERIFIED && Config.REGISTED)
                i  = new Intent(SplashActivity.this,ConfirmActivity.class);
            else
            i  = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(i);

        }catch (Exception e){

        }
        finally {
            finish();
            overridePendingTransition(0,0);
        }
    }
}
