package com.example.shoppingassistant.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.shoppingassistant.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppNavBar appNavBar = new AppNavBar(getSupportFragmentManager());

        BottomNavigationView bottomNavigationView = findViewById(R.id.main_bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(appNavBar);

        appNavBar.updateView();
    }
}
