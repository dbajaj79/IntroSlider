package com.example.introslider.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.example.R;
import com.example.databinding.ActivitySplashScreenBinding;
import com.example.introslider.ui.kotlincode.SliderActivity;

public class Splash_screen extends AppCompatActivity {

    private ActivitySplashScreenBinding activitySplashScreenBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySplashScreenBinding= DataBindingUtil.setContentView(this,R.layout.activity_splash_screen);
        Thread myThread = new Thread()
        {
            @Override
            public void run() {
                try {
                    sleep(1200);
                    Intent intent = new Intent(getApplicationContext(), SliderActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
