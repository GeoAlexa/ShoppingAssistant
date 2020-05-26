package com.example.shoppingassistant.shoplist.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.shoppingassistant.R;
import com.example.shoppingassistant.main.generic.BaseScreen;
import com.example.shoppingassistant.navigation.ScreenTypeEnum;
import com.example.shoppingassistant.shoplist.data_providers.ShopListDataLoader;
import com.example.shoppingassistant.shoplist.adapters.ShoplistAdapter;
import com.example.shoppingassistant.shoplist.interfaces.ShopListItemItemClickListener;
import com.example.shoppingassistant.shoplist.model.ShopList;

import java.util.List;
import java.util.Objects;

public class ShopListScreen extends BaseScreen implements ShopListItemItemClickListener, View.OnClickListener {

    private List<ShopList> shopLists;
    private ShoplistAdapter shoplistAdapter;
    private Button addListBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ShopListDataLoader dataLoader = new ShopListDataLoader();

        dataLoader.loadFromStringArray(getArguments().getStringArrayList("LISTS"));
        shopLists = dataLoader.getShopLists();

        Objects.requireNonNull(getActivity()).getApplicationContext();

        return inflater.inflate(R.layout.shoplist_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shoplistAdapter = new ShoplistAdapter(
                Objects.requireNonNull(getActivity()).getApplicationContext(),
                android.R.layout.simple_list_item_1,
                shopLists
        );
        shoplistAdapter.setShopListItemItemClickListener(this);

        ListView shopListContainerView = Objects.requireNonNull(getView()).findViewById(R.id.shop_list_container);
        shopListContainerView.setAdapter(shoplistAdapter);
        shopListContainerView.setOnItemLongClickListener(shoplistAdapter);

        addListBtn = getActivity().findViewById(R.id.add_list_btn);
        addListBtn.setOnClickListener(this);
    }

    @Override
    public void onShopListLongClicked(ShopList item, int index) {
        Bundle bundle = new Bundle();
        bundle.putString("LIST", item.toJson().toString());
        bundle.putInt("LIST_INDEX", index);

        this.changeScreenListener.sendInfo(bundle);
        this.changeScreenListener.changeScreen(ScreenTypeEnum.SCREEN_TYPE_EDIT_LIST);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == addListBtn.getId()) {
            ShopList item = new ShopList("new_shoplist");
            shopLists.add(item);

            Bundle bundle = new Bundle();
            bundle.putString("LIST", item.toJson().toString());
            bundle.putInt("LIST_INDEX", shopLists.size() - 1);

            this.changeScreenListener.sendInfo(bundle);
            this.changeScreenListener.changeScreen(ScreenTypeEnum.SCREEN_TYPE_EDIT_LIST);
        }
    }
}
