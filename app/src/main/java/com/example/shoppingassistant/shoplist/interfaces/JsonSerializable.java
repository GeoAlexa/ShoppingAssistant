package com.example.shoppingassistant.shoplist.interfaces;

import org.json.JSONException;
import org.json.JSONObject;

public interface JsonSerializable {
    JSONObject toJson() throws JSONException;
    void fromJson(JSONObject object) throws JSONException;
}
