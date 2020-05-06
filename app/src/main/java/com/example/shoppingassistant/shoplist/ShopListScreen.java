package com.example.shoppingassistant.shoplist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shoppingassistant.R;

import org.json.JSONException;
import org.json.JSONObject;

public class ShopListScreen extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ShopListItem item = new ShopListItem("Test");
        item.addTag("food");
        item.addTag("expensive");
        item.addTag("crazy");

        ShopListItem item2 = new ShopListItem("Test2");
        ShopListItem item3 = new ShopListItem("Test3");
        item3.addTag("nada");
        item3.addTag("another");
        item3.addTag("aaaa");

        ShopList list = new ShopList("list1");
        list.addShopListItem(item);
        list.addShopListItem(item2);
        list.addShopListItem(item3);


        try {
            Log.e("TAG_LIST", list.toJson().toString());
            ShopList shoplist = new ShopList("");
            shoplist.fromJson(new JSONObject("{\"title\":\"list1\",\"shop_list_items\":[{\"name\":\"Test\",\"tags\":[\"food\",\"expensive\",\"crazy\"]},{\"name\":\"Test2\",\"tags\":[]},{\"name\":\"Test3\",\"tags\":[\"nada\",\"another\",\"aaaa\"]}]}"));
            Log.e("TAG_LIST2", shoplist.toJson().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return inflater.inflate(R.layout.shoplist_screen, container, false);
    }
}
