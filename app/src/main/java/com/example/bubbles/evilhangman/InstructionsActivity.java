package com.example.bubbles.evilhangman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InstructionsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
    }

    public void back_to_menu_button_click(View view) {
        Intent intent = new Intent(InstructionsActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}
