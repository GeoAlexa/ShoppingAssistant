package com.example.shoppingassistant.shoplist.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.shoppingassistant.R;
import com.example.shoppingassistant.main.generic.BaseScreen;
import com.example.shoppingassistant.navigation.ScreenTypeEnum;
import com.example.shoppingassistant.shoplist.adapters.ShopListItemsAdapter;
import com.example.shoppingassistant.shoplist.model.ShopList;
import com.example.shoppingassistant.shoplist.model.ShopListItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;


public class EditListScreen extends BaseScreen implements View.OnClickListener {

    private ShopList shopList;
    private ShopListItemsAdapter shopListItemsAdapter;
    private Integer listIndex;
    private EditText titleText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.edit_shop_list_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle arguments = getArguments();

        loadCurrentShopList();
        handleAction(arguments);

        updateView();
    }

    private void loadCurrentShopList() {
        shopList = new ShopList("");

        Bundle arguments = this.getArguments();

        if (arguments != null && arguments.getString("LIST") != null) {
            try {
                shopList.fromJson(new JSONObject(Objects.requireNonNull(arguments.getString("LIST"))));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            listIndex = arguments.getInt("LIST_INDEX");
        }

        shopListItemsAdapter = new ShopListItemsAdapter(
                Objects.requireNonNull(getActivity()).getApplicationContext(),
                android.R.layout.simple_list_item_1,
                shopList.getItems()
        );
    }

    private void updateView() {
        titleText = this.getActivity().findViewById(R.id.edit_list_list_name);
        titleText.setText(shopList.getTitle());
        titleText.addTextChangedListener(titleWatcher);

        ((ListView)this.getActivity().findViewById(R.id.edit_list_list_items)).setAdapter(shopListItemsAdapter);
        ((Button)this.getActivity().findViewById(R.id.edit_list_add_new_list_item)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle data = new Bundle();
        data.putString("LIST", this.shopList.toJson().toString());
        data.putInt("INDEX", this.listIndex);

        this.changeScreenListener.sendInfo(data);
        this.changeScreenListener.changeScreen(ScreenTypeEnum.SCREEN_TYPE_EDIT_ITEM);
    }

    private void handleAction(Bundle bundle) {
        if (bundle != null && bundle.getString("ACTION") != null && bundle.getString("ACTION").equals("ADD_NEW_ITEM")) {

            ShopListItem item = new ShopListItem("");
            item.setName(bundle.getString("ITEM_NAME"));

            ArrayList<String> tags = bundle.getStringArrayList("TAGS");
            for (String tag : tags) {
                item.addTag(tag);
            }

            shopList.getItems().add(item);
            shopListItemsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        Bundle data = new Bundle();
        data.putString("SHOP_LIST", shopList.toJson().toString());
        data.putInt("LIST_INDEX", listIndex);
        this.shopListManagementListener.onDataChangedIndex(data, listIndex);
        super.onDestroy();
    }

    private TextWatcher titleWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            shopList.setTitle(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };
}
