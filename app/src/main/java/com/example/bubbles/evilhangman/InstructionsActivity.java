package com.example.bubbles.evilhangman;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.bubbles.evilhangman.Views.CrayonEditText;
import com.example.bubbles.evilhangman.Views.CrayonTextView;

public class InstructionsActivity extends Activity {

    public static final String PREFERENCES_FILE_NAME = "settings";
    public SharedPreferences settings;

    CrayonTextView instructions_start, instructions_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        initialise();
        set_instructions();
    }

    private void initialise(){
        settings = this.getSharedPreferences(PREFERENCES_FILE_NAME, 0);
        instructions_start = (CrayonTextView) findViewById(R.id.instructions_start);
        instructions_end = (CrayonTextView) findViewById(R.id.instructions_end);
    }

    private void set_instructions(){
        if(settings.getBoolean("evil_mode_on", true)) {
            instructions_start.setText(R.string.evil_instructions_start);
            instructions_end.setText(R.string.evil_instructions_end);
        }
        else {
            instructions_start.setText(R.string.instructions_start);
            instructions_end.setText(R.string.instructions_end);
        }
    }

    public void back_to_menu_button_click(View view) {
        Intent intent = new Intent(InstructionsActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}
