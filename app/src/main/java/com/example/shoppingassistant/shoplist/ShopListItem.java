package com.example.shoppingassistant.shoplist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShopListItem implements JsonSerializable {

    private static final String NAME_KEY    = "name";
    private static final String TAG_KEY     = "tags";

    private String name;
    private List<String> tagList;

    ShopListItem(String name) {
        this.name = name;
        this.tagList = new ArrayList<String>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addTag(String tag) {
        tagList.add(tag);
    }

    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject shopListItemJsonObject = new JSONObject();

        JSONArray tagListObject = new JSONArray();
        for (String str : this.tagList) {
            tagListObject.put(str);
        }

        shopListItemJsonObject.put(NAME_KEY, this.name);
        shopListItemJsonObject.put(TAG_KEY, tagListObject);

        return shopListItemJsonObject;
    }

    @Override
    public void fromJson(JSONObject object) throws JSONException {
        this.setName(object.getString(NAME_KEY));

        JSONArray tagListArrayObject = object.getJSONArray(TAG_KEY);

        for (int i = 0; i < tagListArrayObject.length(); ++i) {
            this.addTag(tagListArrayObject.getString(i));
        }
    }
}
