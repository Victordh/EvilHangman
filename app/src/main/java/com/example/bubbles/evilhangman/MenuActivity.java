package com.example.bubbles.evilhangman;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import com.example.bubbles.evilhangman.Views.*;

public class MenuActivity extends Activity {

    public static final String PREFERENCES_FILE_NAME = "settings";
    public SharedPreferences settings;

    CrayonTextView menu_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initialise();

        if(settings.getBoolean("evil_mode_on", true)) {
            menu_title.setText(R.string.evil_menu_title);
        }
        else {
            menu_title.setText(R.string.menu_title);
        }

        // collects about 13 MB of memory
        System.gc();
    }

    private void initialise() {
        settings = this.getSharedPreferences(PREFERENCES_FILE_NAME, 0);
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
