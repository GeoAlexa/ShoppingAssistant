package com.example.shoppingassistant.main;

import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.shoppingassistant.R;
import com.example.shoppingassistant.navigation.NavigationManager;
import com.example.shoppingassistant.navigation.ScreenTypeEnum;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AppNavBar implements BottomNavigationView.OnNavigationItemSelectedListener {

    private NavigationManager screenManager;

    AppNavBar(NavigationManager screenManager) {
        this.screenManager = screenManager;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_menu_home:
                screenManager.openScreen(ScreenTypeEnum.SCREEN_TYPE_HOME);
                break;
            case R.id.main_menu_list:
                screenManager.openScreen(ScreenTypeEnum.SCREEN_TYPE_SHOPLIST);
                break;
            case R.id.main_menu_map:
                screenManager.openScreen(ScreenTypeEnum.SCREEN_TYPE_MAP);
                break;
        }

        return true;
    }
}
