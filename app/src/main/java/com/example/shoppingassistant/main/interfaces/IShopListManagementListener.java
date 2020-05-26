package com.example.shoppingassistant.main.interfaces;

import android.os.Bundle;

public interface IShopListManagementListener {
    void onDataChanged(Bundle data);
    void onDataChangedIndex(Bundle data, int index);
    Bundle getListBundle();
}
