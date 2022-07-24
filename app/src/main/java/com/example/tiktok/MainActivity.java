package com.example.tiktok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

//First Screen : SplashScreen
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Staut Bar Hide Start
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Staut Bar Hide End
        //Handler for redirect
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this , HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}