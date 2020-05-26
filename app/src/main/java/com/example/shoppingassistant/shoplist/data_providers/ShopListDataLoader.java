package com.example.shoppingassistant.shoplist.data_providers;

import android.content.Context;

import com.example.shoppingassistant.shoplist.model.KnownTagsContainer;
import com.example.shoppingassistant.shoplist.model.ShopList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ShopListDataLoader {

    private static final String SHOP_LISTS_KEY   = "shop_lists";
    private static final String KNOWN_TAGS_KEY   = "known_tags";
    private static final String FILE_NAME        = "shoplist_data.json";

    private KnownTagsContainer knownTagsContainer;
    private List<ShopList> shopLists;

    public ShopListDataLoader() {
        knownTagsContainer = new KnownTagsContainer();
        shopLists = new ArrayList<>();
        setKnownTags();
    }

    public void saveShoppingListData(Context appContext) {
        File fileToBeSaved = new File(
                appContext.getExternalCacheDir(),
                FILE_NAME
        );

        try {
            fileToBeSaved.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileToBeSaved, false);
            fileOutputStream.write(buildJsonToBeSaved().toString().getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public List<ShopList> getShopLists() {
        return this.shopLists;
    }

    public void loadFromStringArray(List<String> serializedShopLists) {
        this.shopLists.clear();
        for (String shopListString : serializedShopLists) {
            try {
                ShopList sl = new ShopList("");
                sl.fromJson(new JSONObject(shopListString));
                this.shopLists.add(sl);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadShoppinListData(Context appContext) {
        File fileToRead = new File(
                appContext.getExternalCacheDir(),
                FILE_NAME
        );

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fileToRead);
            byte[] data = new byte[(int) fileToRead.length()];
            fis.read(data);
            fis.close();

            JSONObject object = new JSONObject(new String(data, StandardCharsets.UTF_8));
//            loadKnownTags(object.getJSONArray(KNOWN_TAGS_KEY));
            setKnownTags();
            loadListsFromJsonArray(object.getJSONArray(SHOP_LISTS_KEY));

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }


    }

    private JSONArray saveKnownTags() throws JSONException {
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < knownTagsContainer.length(); ++i) {
            jsonArray.put(knownTagsContainer.getTag(i));
        }

        return  jsonArray;
    }

    private JSONArray saveShopLists() throws JSONException {
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < shopLists.size(); ++i) {
            jsonArray.put(shopLists.get(i).toJson().toString());
        }

        return jsonArray;
    }

    private JSONObject buildJsonToBeSaved() throws JSONException {
        JSONObject object = new JSONObject();

        object.put(KNOWN_TAGS_KEY, this.saveKnownTags());
        object.put(SHOP_LISTS_KEY, this.saveShopLists());

        return object;
    }

    private void setKnownTags() {
        knownTagsContainer.addTag("Food")
                .addTag("Clothing")
                .addTag("Entertainment")
                .addTag("Electronics");
    }

    private void loadListsFromJsonArray(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); ++i) {
            try {
                ShopList sl = new ShopList("");
                sl.fromJson(new JSONObject(jsonArray.getString(i)));
                shopLists.add(sl);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadKnownTags(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); ++i) {
            try {
                knownTagsContainer.addTag(jsonArray.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
