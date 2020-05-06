package com.example.shoppingassistant.main;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.shoppingassistant.R;
import com.example.shoppingassistant.home.HomeScreen;
import com.example.shoppingassistant.map.MapScreen;
import com.example.shoppingassistant.shoplist.ShopListScreen;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AppNavBar implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    private Fragment        currentFragment;

    AppNavBar(FragmentManager fragManager) {
        fragmentManager = fragManager;
        currentFragment = new HomeScreen();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_menu_home:
                currentFragment = new HomeScreen();
                break;
            case R.id.main_menu_list:
                currentFragment = new ShopListScreen();
                break;
            case R.id.main_menu_map:
                currentFragment = new MapScreen();
                break;
        }
        updateView();

        return true;
    }

    void updateView() {
        fragmentManager.beginTransaction().replace(R.id.main_frame_layout, currentFragment).commit();
    }

}
