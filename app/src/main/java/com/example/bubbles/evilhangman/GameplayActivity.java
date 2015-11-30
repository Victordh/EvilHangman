package com.example.bubbles.evilhangman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

public class GameplayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

    }

    // picks a word randomly from the dictionary
    private void random_word(){
        Integer random_number = new Random().nextInt(getResources().getStringArray(R.array.words_small).length);
        String[] array = getResources().getStringArray(R.array.words_small);
        String word_picked = array[random_number];
        String the_word_was = word_picked;
    }

    public void back_to_menu_button_click(View view) {
        Intent intent = new Intent(GameplayActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}
