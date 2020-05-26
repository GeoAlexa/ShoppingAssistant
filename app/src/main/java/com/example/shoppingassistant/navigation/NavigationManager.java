package com.example.shoppingassistant.navigation;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import com.example.shoppingassistant.R;
import com.example.shoppingassistant.home.HomeScreen;
import com.example.shoppingassistant.main.generic.BaseScreen;
import com.example.shoppingassistant.main.interfaces.IChangeScreenListener;
import com.example.shoppingassistant.main.interfaces.IShopListManagementListener;
import com.example.shoppingassistant.map.MapScreen;
import com.example.shoppingassistant.shoplist.view.EditListItemScreen;
import com.example.shoppingassistant.shoplist.view.EditListScreen;
import com.example.shoppingassistant.shoplist.view.ShopListScreen;

import java.util.Stack;

public class NavigationManager {

    private FragmentManager fragmentManager;
    private BaseScreen currentScreen;
    private IChangeScreenListener changeScreenListener;
    private IShopListManagementListener shopListManagementListener;
    private Bundle currentBundleData;

    public NavigationManager(FragmentManager fragmentManager) {
        this.fragmentManager    = fragmentManager;
        this.currentScreen      = new HomeScreen();
    }

    public void openScreen(ScreenTypeEnum screenType) {
        updateCurrentScreen(screenType);
        this.currentScreen.setArguments(currentBundleData);
        fragmentManager.beginTransaction().replace(R.id.main_frame_layout, currentScreen).commit();
    }

    public void registerChangeScreenListener(IChangeScreenListener changeScreenListener) {
        this.changeScreenListener = changeScreenListener;
    }

    public void registerShopListManagerListener(IShopListManagementListener shopListManagementListener) {
        this.shopListManagementListener = shopListManagementListener;
    }

    private void updateCurrentScreen(ScreenTypeEnum screenType) {
        switch (screenType) {
            case SCREEN_TYPE_HOME:
                this.currentScreen = new HomeScreen();
                break;
            case SCREEN_TYPE_MAP:
                this.currentScreen = new MapScreen();
                break;
//            case SCREEN_TYPE_ABOUT:
//                this.currentScreen = new AboutScreen();
//                break;
            case SCREEN_TYPE_SHOPLIST:
                setBundleData(this.shopListManagementListener.getListBundle());
                this.currentScreen = new ShopListScreen();
                break;
            case SCREEN_TYPE_EDIT_LIST:
                this.currentScreen = new EditListScreen();
                break;
            case SCREEN_TYPE_EDIT_ITEM:
                this.currentScreen = new EditListItemScreen();
                break;
            default:
                System.out.println("Unknown screen");
                break;
        }

        this.currentScreen.registerChangeScreenListener(this.changeScreenListener);
        this.currentScreen.registerShopListDataListener(this.shopListManagementListener);
    }

    public void setBundleData(Bundle info) {
        currentBundleData = info;
    }
}
