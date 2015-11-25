package com.example.bubbles.evilhangman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void back_to_menu_button_click(View view) {
        Intent intent = new Intent(SettingsActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}
