package com.example.introslider;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.example.R;
import com.example.databinding.ActivityMainBinding;
import com.example.introslider.ui.MainScreen;
import com.example.introslider.ui.PreferenceManager;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        activityMainBinding.b1.setText(Html.fromHtml("&#175"));
    }

    public void replay(View view) {
        PreferenceManager preferenceManager = new PreferenceManager(getApplicationContext());
        preferenceManager.setFirstTimeLaunch(true);
        startActivity(new Intent(MainActivity.this, MainScreen.class));
        finish();
    }
}
