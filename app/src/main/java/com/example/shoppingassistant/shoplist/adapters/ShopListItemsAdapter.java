package com.example.shoppingassistant.shoplist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.shoppingassistant.R;
import com.example.shoppingassistant.shoplist.model.ShopList;
import com.example.shoppingassistant.shoplist.model.ShopListItem;

import java.util.List;

public class ShopListItemsAdapter extends ArrayAdapter<ShopListItem> {

    private List<ShopListItem> items;
    private Context context;

    public ShopListItemsAdapter(@NonNull Context context, int resource, @NonNull  List<ShopListItem> objects) {
        super(context, resource, objects);
        this.items = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ShopListItem shopListItem = items.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.shop_list_item_info, parent, false);
        }

        TextView textView = (TextView)convertView.findViewById(R.id.shop_list_item_view_title);
        textView.setText(shopListItem.getName());

        return convertView;
    }
}
