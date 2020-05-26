package com.example.shoppingassistant.main.interfaces;

import android.os.Bundle;

import com.example.shoppingassistant.navigation.ScreenTypeEnum;

public interface IChangeScreenListener {
    void changeScreen(ScreenTypeEnum screenType);
    void sendInfo(Bundle info);
}
