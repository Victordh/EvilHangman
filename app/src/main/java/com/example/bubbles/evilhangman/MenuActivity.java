package com.example.bubbles.evilhangman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void new_game_button_click(View view) {
        Intent intent = new Intent(MenuActivity.this, GameplayActivity.class);
        startActivity(intent);
    }

    public void instructions_button_click(View view) {
        Intent intent = new Intent(MenuActivity.this, InstructionsActivity.class);
        startActivity(intent);
    }

    public void high_scores_button_click(View view) {
        Intent intent = new Intent(MenuActivity.this, HighScoresActivity.class);
        startActivity(intent);
    }

    public void settings_button_click(View view) {
        Intent intent = new Intent(MenuActivity.this, SettingsActivity.class);
        startActivity(intent);
    }
}
