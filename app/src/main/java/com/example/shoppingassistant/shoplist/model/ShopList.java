package com.example.shoppingassistant.shoplist.model;

import com.example.shoppingassistant.shoplist.interfaces.JsonSerializable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShopList implements JsonSerializable {

    private static final String TITLE_KEY = "title";
    private static final String SHOP_LIST_KEY = "shop_list_items";

    private String title;
    private List<ShopListItem> shopListItems;

    public ShopList(String shopListTitle) {
        this.title = shopListTitle;
        this.shopListItems = new ArrayList<ShopListItem>();
    }

    public String getTitle() {
        return title;
    }

    public List<ShopListItem> getItems() {return  shopListItems;}

    public void setTitle(String title) {
        this.title = title;
    }

    public void addShopListItem(ShopListItem item) {
        this.shopListItems.add(item);
    }

    public ShopListItem get(int index) {
        return this.shopListItems.get(index);
    }

    @Override
    public JSONObject toJson(){
        JSONObject shopListItemJsonObject = new JSONObject();

        JSONArray shopListObjcets = new JSONArray();
        for (ShopListItem item : this.shopListItems) {
            try {
                shopListObjcets.put(item.toJson());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        try {
            shopListItemJsonObject.put(TITLE_KEY, this.title);
            shopListItemJsonObject.put(SHOP_LIST_KEY, shopListObjcets);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return shopListItemJsonObject;
    }

    @Override
    public void fromJson(JSONObject object){
        try {
            this.setTitle(object.getString(TITLE_KEY));

            JSONArray array = object.getJSONArray(SHOP_LIST_KEY);

            for (int i = 0; i < array.length(); ++i) {
                ShopListItem item = new ShopListItem("");
                item.fromJson(array.getJSONObject(i));

                this.addShopListItem(item);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
