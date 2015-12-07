package com.example.bubbles.evilhangman;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.bubbles.evilhangman.Views.CrayonButton;
import com.example.bubbles.evilhangman.Views.CrayonTextView;

public class GameplayActivity extends Activity {

    public static final String PREFERENCES_FILE_NAME = "settings";
    public SharedPreferences settings;
    public static Context context;

    CrayonTextView questionmarks, letters_tried, guesses_left;
    CrayonButton guess_button;
    ImageView hangman;
    EditText letter_input;

    static String[] words;
    String word;
    GoodGameplay goodgameplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);
        context = getApplicationContext();
        goodgameplay = new GoodGameplay();
        words = getResources().getStringArray(R.array.words);


        initialise();
        reset();
    }

    public static Context getContext() {
        return context;
    }

    private void initialise() {
        settings = this.getSharedPreferences(PREFERENCES_FILE_NAME, 0);
        guess_button = (CrayonButton) findViewById(R.id.guess_button);
        questionmarks = (CrayonTextView) findViewById(R.id.questionmarks);
        letter_input = (EditText) findViewById(R.id.letter_input);
        letters_tried = (CrayonTextView) findViewById(R.id.letters_tried);
        guesses_left = (CrayonTextView) findViewById(R.id.guesses_left);
        hangman = (ImageView) findViewById(R.id.hangman);
    }

    public void back_to_menu_button_click(View view) {
        Intent intent = new Intent(GameplayActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    public void guess_button_click(View view) {
        String input = letter_input.getText().toString();
        String tried = letters_tried.getText().toString();
        // check if the input is a valid letter and not tried yet
        String letter = goodgameplay.get_letter_input(input, tried);
        if (!letter.equals("false")){
            // add letter to letters tried
            letters_tried.setText(tried + " " + letter);

            String qmarks = questionmarks.getText().toString();
            // check if the letter is in the word
            String temp = goodgameplay.letter_in_word_picked(letter, qmarks);
            // if it is, change placeholders
            if (!qmarks.equals(temp)) {
                questionmarks.setText(temp);
                // shows win picture if user wins
                show_picture_at_win(temp);
            }
            // if it's not, remove one guess and adjust the picture
            else {
                String guesses = guesses_left.getText().toString();
                int amount = goodgameplay.remove_one_guess(guesses);

                guesses_left.setText(Integer.toString(amount));
                adjust_picture(amount);
                // shows word if user loses
                show_word_at_loss();
            }
        }
        // clears user input from EditText
        letter_input.setText("");
    }

    public void new_word_button_click(View view) {
        reset();
    }

    // picks a word and sets questionmarks accordingly
    // resets guesses left, letters tried, picture
    private void reset() {
        if(!settings.getBoolean("evil_mode_on", true)) {
            word = goodgameplay.random_word();
        }
        questionmarks.setText(goodgameplay.set_questionmarks());
        set_questionmark_size();

        letters_tried.setText("");
        guess_button.setVisibility(View.VISIBLE);
        hangman.setImageResource(R.drawable.over_10_left);
        String guesses_allowed = String.valueOf(settings.getInt("guesses_allowed_value", 6));
        guesses_left.setText(guesses_allowed);
    }

    // adjusts the text size according to the length of the word
    private void set_questionmark_size(){
        switch (settings.getInt("word_length_value", 9)) {
            default:
                questionmarks.setTextSize(48);
                break;
            case 9:
                questionmarks.setTextSize(42);
                break;
            case 10:
                questionmarks.setTextSize(38);
                break;
            case 11:
                questionmarks.setTextSize(34);
                break;
            case 12:
                questionmarks.setTextSize(30);
                break;
            case 13:
                questionmarks.setTextSize(28);
                break;
            case 14:
                questionmarks.setTextSize(26);
                break;
            case 15:
                questionmarks.setTextSize(24);
                break;
            case 16:
                questionmarks.setTextSize(22);
                break;
        }
    }

    private void show_picture_at_win(String temp){
        if (goodgameplay.word_revealed(temp)) {
            hangman.setImageResource(R.drawable.win);
            guess_button.setVisibility(View.INVISIBLE);
        }
    }

    // displays the word if the user loses
    private void show_word_at_loss() {
        if (guesses_left.getText().charAt(0) == '0') {
            guess_button.setVisibility(View.INVISIBLE);
            questionmarks.setText(goodgameplay.the_word_was());
        }
    }

    // displays the correct picture given the amount of guesses left
    private void adjust_picture(int amount) {
        String image;
        if(amount > 10) {
            image = "over_10_left";
        }
        else {
            image = "left_" + Integer.toString(amount);
        }
        int resID = getResources().getIdentifier(image, "drawable", getPackageName());
        hangman.setImageResource(resID);
    }
}
