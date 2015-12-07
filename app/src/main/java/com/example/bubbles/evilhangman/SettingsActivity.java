package com.example.bubbles.evilhangman;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;

import com.example.bubbles.evilhangman.Views.CrayonEditText;
import com.example.bubbles.evilhangman.Views.CrayonTextView;

public class SettingsActivity extends Activity {

    public static final String PREFERENCES_FILE_NAME = "settings";
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    CrayonTextView word_length_label, guesses_allowed_label, evil_mode_text;
    CrayonEditText name_edittext;
    SeekBar word_length_seekbar, guesses_allowed_seekbar;
    Switch evil_mode_switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initialise();
        get_values_from_prefs();

        name_edittext.addTextChangedListener(name_listener());
        word_length_seekbar.setOnSeekBarChangeListener(word_length_listener);
        guesses_allowed_seekbar.setOnSeekBarChangeListener(guesses_allowed_listener);
        evil_mode_switch.setOnCheckedChangeListener(evil_mode_listener);
    }

    private void initialise() {
        settings = this.getSharedPreferences(PREFERENCES_FILE_NAME, 0);
        word_length_label = (CrayonTextView) findViewById(R.id.word_length_label);
        guesses_allowed_label = (CrayonTextView) findViewById(R.id.guesses_allowed_label);
        evil_mode_text = (CrayonTextView) findViewById(R.id.evil_mode_text);
        name_edittext = (CrayonEditText) findViewById(R.id.name_edittext);
        word_length_seekbar = (SeekBar) findViewById(R.id.word_length_seekbar);
        guesses_allowed_seekbar = (SeekBar) findViewById(R.id.guesses_allowed_seekbar);
        evil_mode_switch = (Switch) findViewById(R.id.evil_mode_switch);
    }

    private void get_values_from_prefs() {
        String name = settings.getString("name", getString(R.string.name_default));
        name_edittext.setText(name);

        int word_length_value = settings.getInt("word_length_value", 9);
        word_length_seekbar.setProgress(word_length_value);
        word_length_label.setText(String.valueOf(word_length_value));

        int guesses_allowed_value = settings.getInt("guesses_allowed_value", 6);
        guesses_allowed_seekbar.setProgress(guesses_allowed_value);
        guesses_allowed_label.setText(String.valueOf(guesses_allowed_value));

        boolean evil_mode_on = settings.getBoolean("evil_mode_on", true);
        evil_mode_switch.setChecked(evil_mode_on);
        if(evil_mode_on){
            evil_mode_text.setText(R.string.evil_mode_on);
        }
        else {
            evil_mode_text.setText(R.string.evil_mode_off);
        }
    }

    private TextWatcher name_listener() {

        return new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                editor = settings.edit();
                editor.putString("name", s.toString());
                editor.apply();
            }
        };
    }

    private SeekBar.OnSeekBarChangeListener word_length_listener = new SeekBar.OnSeekBarChangeListener() {
        int value;
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            word_length_label.setText(String.valueOf(progress + 1));
            value = progress + 1;
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar){}
        @Override
        public void onStopTrackingTouch(SeekBar seekBar){
            editor = settings.edit();
            editor.putInt("word_length_value", value);
            editor.apply();
        }
    };

    private SeekBar.OnSeekBarChangeListener guesses_allowed_listener = new SeekBar.OnSeekBarChangeListener() {
        int value;
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            guesses_allowed_label.setText(String.valueOf(progress + 1));
            value = progress + 1;
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar){}
        @Override
        public void onStopTrackingTouch(SeekBar seekBar){
            editor = settings.edit();
            editor.putInt("guesses_allowed_value", value);
            editor.apply();
        }
    };

    private Switch.OnCheckedChangeListener evil_mode_listener = new Switch.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
            if(isChecked){
                evil_mode_text.setText(R.string.evil_mode_on);
            }
            else {
                evil_mode_text.setText(R.string.evil_mode_off);
            }
            editor = settings.edit();
            editor.putBoolean("evil_mode_on", isChecked);
            editor.apply();
        }
    };

    public void back_to_menu_button_click(View view) {
        Intent intent = new Intent(SettingsActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    public void settings_reset_button_click(View view) {
        name_edittext.setText(R.string.name_default);
        word_length_seekbar.setProgress(9 - 1);
        guesses_allowed_seekbar.setProgress(6 - 1);
        evil_mode_switch.setChecked(true);
    }
}
