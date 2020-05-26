package com.example.shoppingassistant.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.shoppingassistant.R;
import com.example.shoppingassistant.main.generic.BaseScreen;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class HomeScreen extends BaseScreen implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        registerButtonsListener();
    }

    @Override
    public void onClick(View v) {
        BottomNavigationView bottomNavigationView = Objects.requireNonNull(getActivity()).findViewById(R.id.main_bottom_nav);

        switch (v.getId()) {
            case R.id.home_screen_button_home:
                bottomNavigationView.findViewById(R.id.main_menu_home).performClick();
                break;
            case R.id.home_screen_button_list:
                bottomNavigationView.findViewById(R.id.main_menu_list).performClick();
                break;
            default:
                System.out.println("OUT");
                break;
        }
    }

    private void registerButtonsListener() {
        ((Button)this.getActivity().findViewById(R.id.home_screen_button_home)).setOnClickListener(this);
        ((Button)this.getActivity().findViewById(R.id.home_screen_button_list)).setOnClickListener(this);
    }
}
