package com.example.shoppingassistant.main.generic;

import androidx.fragment.app.Fragment;

import com.example.shoppingassistant.main.interfaces.IChangeScreenListener;
import com.example.shoppingassistant.main.interfaces.IShopListManagementListener;

public class BaseScreen extends Fragment {

    protected IChangeScreenListener changeScreenListener;
    protected IShopListManagementListener shopListManagementListener;

    public void registerChangeScreenListener(IChangeScreenListener listener) {
        this.changeScreenListener = listener;
    }

    public void registerShopListDataListener(IShopListManagementListener shopListManagementListener) {
        this.shopListManagementListener = shopListManagementListener;
    }

}
