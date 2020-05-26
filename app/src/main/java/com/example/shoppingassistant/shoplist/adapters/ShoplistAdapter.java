package com.example.shoppingassistant.shoplist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.shoppingassistant.R;
import com.example.shoppingassistant.shoplist.interfaces.ShopListItemItemClickListener;
import com.example.shoppingassistant.shoplist.model.ShopList;

import java.util.List;

public class ShoplistAdapter extends ArrayAdapter<ShopList> implements AdapterView.OnItemLongClickListener {

    private List<ShopList> lists;
    private Context context;
    private ShopListItemItemClickListener shopListItemItemClickListener;

    public ShoplistAdapter(@NonNull Context context, int resource, @NonNull List<ShopList> objects) {
        super(context, resource, objects);
        this.context = context;
        this.lists = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ShopList shopList = lists.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.shop_list_title, parent, false);
        }

        TextView textView = (TextView)convertView.findViewById(R.id.shop_list_id);
        textView.setText(shopList.getTitle());

        return convertView;
    }

    public void setShopListItemItemClickListener(ShopListItemItemClickListener listener)
    {
        shopListItemItemClickListener = listener;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        shopListItemItemClickListener.onShopListLongClicked(lists.get(position), position);
        return false;
    }
}
