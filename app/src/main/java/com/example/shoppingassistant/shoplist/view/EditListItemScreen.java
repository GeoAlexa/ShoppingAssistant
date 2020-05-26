package com.example.shoppingassistant.shoplist.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.shoppingassistant.R;
import com.example.shoppingassistant.main.generic.BaseScreen;
import com.example.shoppingassistant.navigation.ScreenTypeEnum;

import java.util.ArrayList;
import java.util.List;

public class EditListItemScreen extends BaseScreen implements View.OnClickListener {

    private List<CheckBox> tagSelectorList;
    private Button         saveButton;
    private Button         cancelButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.edit_list_item_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getViewElements();
        super.onViewCreated(view, savedInstanceState);
    }

    private void getViewElements() {
        loadTagSelectorList();
        saveButton = this.getActivity().findViewById(R.id.edit_list_item_btn_save);
        cancelButton = this.getActivity().findViewById(R.id.edit_list_item_btn_cancel);
        saveButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    private void loadTagSelectorList() {
        tagSelectorList = new ArrayList<>();
        tagSelectorList.add((CheckBox) this.getActivity().findViewById(R.id.food_tag_id));
        tagSelectorList.add((CheckBox) this.getActivity().findViewById(R.id.electronics_tag_id));
        tagSelectorList.add((CheckBox) this.getActivity().findViewById(R.id.entertainment_tag_id));
        tagSelectorList.add((CheckBox) this.getActivity().findViewById(R.id.clothing_tag_id));
    }

    @Override
    public void onClick(View v) {
        if (saveButton.getId() == v.getId()) {
            this.changeScreenListener.sendInfo(loadDataBeforeSave());
            this.changeScreenListener.changeScreen(ScreenTypeEnum.SCREEN_TYPE_EDIT_LIST);
            return;
        }

        if (cancelButton.getId() == v.getId()) {
            this.changeScreenListener.changeScreen(ScreenTypeEnum.SCREEN_TYPE_EDIT_LIST);
            return;
        }

        Log.e("GENERAL", "Unknown click action");
    }

    private Bundle loadDataBeforeSave()
    {
        Bundle bundle = new Bundle();
        bundle.putString("ACTION", "ADD_NEW_ITEM");

        ArrayList<String> list = new ArrayList<>();
        for (CheckBox cb : tagSelectorList) {
            if (cb.isChecked()) {
                list.add((String) cb.getText());
            }
        }

        bundle.putStringArrayList("TAGS", list);

        EditText itemNameEditText = ((EditText)this.getActivity().findViewById(R.id.edit_list_item_item_name));
        bundle.putString("ITEM_NAME", itemNameEditText .getText().toString());
        bundle.putString("LIST", getArguments() != null ? getArguments().getString("LIST") : "");
        bundle.putInt("LIST_INDEX", getArguments() != null ? getArguments().getInt("INDEX") : -1);

        return bundle;
    }
}
