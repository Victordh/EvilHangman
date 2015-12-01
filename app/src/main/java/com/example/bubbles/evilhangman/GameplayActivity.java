package com.example.bubbles.evilhangman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.bubbles.evilhangman.Views.CrayonButton;
import com.example.bubbles.evilhangman.Views.CrayonTextView;

public class GameplayActivity extends Activity {

    static String[] words;
    CrayonTextView questionmarks, letters_tried;
    CrayonButton guess_button;
    String word_picked, letter;
    Gameplay gameplay;
    EditText letter_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);
        gameplay = new Gameplay();
        words = getResources().getStringArray(R.array.words_short);

        initialise();
        reset();
    }

    private void initialise() {
        guess_button = (CrayonButton) findViewById(R.id.guess_button);
        questionmarks = (CrayonTextView) findViewById(R.id.questionmarks);
        letter_input = (EditText) findViewById(R.id.letter_input);
        letters_tried = (CrayonTextView) findViewById(R.id.letters_tried);
    }

    public void back_to_menu_button_click(View view) {
        Intent intent = new Intent(GameplayActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    public void guess_button_click(View view) {
        if (get_letter_input()){
            String qmarks = questionmarks.getText().toString();
            String temp = gameplay.letter_in_word_picked(letter, qmarks);
            questionmarks.setText(temp);
        }
        letter_input.setText("");
    }

    public void new_word_button_click(View view) {
        reset();
    }

    private void reset(){
        word_picked = gameplay.random_word();
        questionmarks.setText(gameplay.set_questionmarks());
        set_questionmark_size();
        letters_tried.setText(R.string.letters_tried);
    }

    private void set_questionmark_size(){
        switch (word_picked.length()) {
            default:
                questionmarks.setTextSize(48);
                break;
            case 9:
                questionmarks.setTextSize(44);
                break;
            case 10:
                questionmarks.setTextSize(40);
                break;
            case 11:
                questionmarks.setTextSize(36);
                break;
            case 12:
                questionmarks.setTextSize(32);
                break;
            case 13:
                questionmarks.setTextSize(30);
                break;
            case 14:
                questionmarks.setTextSize(28);
                break;
            case 15:
                questionmarks.setTextSize(26);
                break;
            case 16:
                questionmarks.setTextSize(24);
                break;
        }
    }

    // returns the character input if it's a letter and not tried yet
    private boolean get_letter_input() {
        // takes the first character of user input (disregards if no input)
        if (letter_input.getText().toString().length() != 0) {
            String l = letter_input.getText().toString().substring(0, 1).toLowerCase();
            // checks if the character is a letter
            if (l.matches("[a-z]+")) {
                // checks with already tried letters
                String tried = letters_tried.getText().toString();
                if (!tried.contains(l)){
                    letters_tried.setText(tried + " " + l);
                    letter = l;
                    return true;
                }
            }
        }
        return false;
    }
}
