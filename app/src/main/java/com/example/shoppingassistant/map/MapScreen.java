package com.example.shoppingassistant.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.shoppingassistant.R;
import com.example.shoppingassistant.main.generic.BaseScreen;

public class MapScreen extends BaseScreen {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.map_screen, container, false);
    }
}
