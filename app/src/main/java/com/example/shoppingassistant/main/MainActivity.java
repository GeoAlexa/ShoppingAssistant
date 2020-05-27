package com.example.shoppingassistant.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.shoppingassistant.R;
import com.example.shoppingassistant.main.interfaces.IChangeScreenListener;
import com.example.shoppingassistant.main.interfaces.IShopListManagementListener;
import com.example.shoppingassistant.navigation.NavigationManager;
import com.example.shoppingassistant.navigation.ScreenTypeEnum;
import com.example.shoppingassistant.shoplist.data_providers.ShopListDataLoader;
import com.example.shoppingassistant.shoplist.model.ShopList;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements IChangeScreenListener, IShopListManagementListener {

    public static final int PERMISSION_WRITE_EXTERNAL_STORAGE = 100;
    public static final int PERMISSION_READ_EXTERNAL_STORAGE  = 101;
    public static final int PERMISSION_ACCESS_COARSE_LOCATION = 102;
    public static final int PERMISSION_ACCESS_FINE_LOCATION   = 103;

    private Map<Integer, String> permissionCodeMap;
    private NavigationManager navManager;

    private ShopListDataLoader shopListDataLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shopListDataLoader = new ShopListDataLoader();
        shopListDataLoader.loadShoppinListData(this);

        loadPermissionsMap();

        requestAccess();

        loadNavigation();

        loadBottomNavigationView();



        this.navManager.openScreen(ScreenTypeEnum.SCREEN_TYPE_HOME);
    }

    private void loadBottomNavigationView() {
        AppNavBar appNavBar = new AppNavBar(this.navManager);
        BottomNavigationView bottomNavigationView = findViewById(R.id.main_bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(appNavBar);
    }

    private void loadNavigation() {
        this.navManager = new NavigationManager(getSupportFragmentManager());
        this.navManager.registerChangeScreenListener(this);
        this.navManager.registerShopListManagerListener(this);
    }

    private void requestAccess() {
        Integer[] requiredPermissions = {
                PERMISSION_WRITE_EXTERNAL_STORAGE,
                PERMISSION_READ_EXTERNAL_STORAGE,
                PERMISSION_ACCESS_COARSE_LOCATION,
                PERMISSION_ACCESS_FINE_LOCATION
        };

        askForPermissions(requiredPermissions);
    }

    public void askForPermissions(Integer[] permissions) {
        for (Integer permission : permissions) {
            if (!isPermissionGranted(permission)) {
                requestPermission(permission);
            }
        }
    }

    private boolean isPermissionGranted(Integer permissionCode) {
        return ActivityCompat.checkSelfPermission(getApplicationContext(), Objects.requireNonNull(permissionCodeMap.get(permissionCode))) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission(Integer permissionCode) {
        requestPermissions(new String[]{permissionCodeMap.get(permissionCode)}, permissionCode);
    }

    private void loadPermissionsMap() {
        permissionCodeMap = new HashMap<>();
        permissionCodeMap.put(PERMISSION_WRITE_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permissionCodeMap.put(PERMISSION_READ_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
        permissionCodeMap.put(PERMISSION_ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION);
        permissionCodeMap.put(PERMISSION_ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    @Override
    public void changeScreen(ScreenTypeEnum screenType) {
        navManager.openScreen(screenType);
    }

    @Override
    public void sendInfo(Bundle info) {
        navManager.setBundleData(info);
    }

    @Override
    public void onDataChanged(Bundle data) {
        shopListDataLoader.loadFromStringArray(Objects.requireNonNull(data.getStringArrayList("LISTS")));
        shopListDataLoader.saveShoppingListData(this);
    }

    public void onDataChangedIndex(Bundle data, int index) {
        ShopList sl = new ShopList("");
        try {
            sl.fromJson(new JSONObject(data.getString("SHOP_LIST", "")));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (index < shopListDataLoader.getShopLists().size()) {
            shopListDataLoader.getShopLists().set(index, sl);
        } else {
            shopListDataLoader.getShopLists().add(sl);
        }

        shopListDataLoader.saveShoppingListData(this);
    }

    @Override
    public Bundle getListBundle()
    {
        Bundle result = new Bundle();
        ArrayList<String> list = new ArrayList<String>();
        for (ShopList sl : shopListDataLoader.getShopLists()) {
            list.add(sl.toJson().toString());
        }

        result.putStringArrayList("LISTS", list);

        return  result;
    }
}
