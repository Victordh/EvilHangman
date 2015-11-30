package com.example.bubbles.evilhangman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.bubbles.evilhangman.Views.*;

public class MenuActivity extends Activity {

    CrayonTextView menu_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initialise_elements();
        // collects about 13 MB of memory
        System.gc();
    }

    private void initialise_elements() {
        menu_title = (CrayonTextView) findViewById(R.id.menu_title);
    }

    public void play_button_click(View view) {
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
