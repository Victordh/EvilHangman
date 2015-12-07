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
    SharedPreferences.Editor editor;
    public static Context context;
    GoodGameplay goodgameplay;

    CrayonTextView questionmarks, letters_tried, guesses_left;
    CrayonButton guess_button;
    ImageView hangman;
    EditText letter_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);
        context = getApplicationContext();
        goodgameplay = new GoodGameplay();

        initialise();
        goodgameplay.load_words();
        first_time();
        get_gamestate_preferences();
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
        set_gamestate_preferences();

    }

    public void new_word_button_click(View view) {
        reset();
    }

    // picks a word and sets questionmarks accordingly
    // resets guesses left, letters tried, picture
    private void reset() {
        start_game_preferences();
        if(!settings.getBoolean("evil_mode_on", true)) {
            goodgameplay.random_word();
        }
        questionmarks.setText(goodgameplay.set_questionmarks(true));
        set_questionmark_size(settings.getInt("word_length_value", 9));

        letters_tried.setText("");
        guess_button.setVisibility(View.VISIBLE);
        letter_input.setVisibility(View.VISIBLE);
        int amount = settings.getInt("guesses_allowed_value", 6);
        adjust_picture(amount);
        guesses_left.setText(String.valueOf(amount));
        set_gamestate_preferences();
    }

    // adjusts the text size according to the length of the word
    private void set_questionmark_size(int length){
        switch (length) {
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

    // displays win picture if user wins
    private void show_picture_at_win(String temp){
        if (goodgameplay.word_revealed(temp)) {
            hangman.setImageResource(R.drawable.win);
            guess_button.setVisibility(View.INVISIBLE);
            letter_input.setVisibility(View.INVISIBLE);
        }
    }

    // displays the word if the user loses
    private void show_word_at_loss() {
        if (guesses_left.getText().charAt(0) == '0') {
            questionmarks.setText(goodgameplay.the_word_was());
            hangman.setImageResource(R.drawable.left_0);
            guess_button.setVisibility(View.INVISIBLE);
            letter_input.setVisibility(View.INVISIBLE);
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

    // retrieves settings
    private void start_game_preferences() {
        editor = settings.edit();
        editor.putBoolean("game_evil_mode_on", settings.getBoolean("evil_mode_on", true));
        editor.putString("game_name", settings.getString("name", "Player001"));
        editor.putInt("game_word_length", settings.getInt("word_length_value", 9));
        editor.putInt("game_guesses_allowed", settings.getInt("guesses_allowed_value", 6));
        editor.apply();
    }

    // saves game progress
    private void set_gamestate_preferences() {
        editor = settings.edit();
        editor.putString("game_guesses_left", guesses_left.getText().toString());
        editor.putString("game_letters_tried", letters_tried.getText().toString());
        editor.putString("game_word_picked", goodgameplay.word_picked);
        editor.putString("game_word_progress", questionmarks.getText().toString());
        editor.apply();
    }

    // retrieves game progress
    private void get_gamestate_preferences() {
        String amount = settings.getString("game_guesses_left", guesses_left.getText().toString());
        guesses_left.setText(amount);
        adjust_picture(Integer.valueOf(amount));

        letters_tried.setText(settings.getString("game_letters_tried", letters_tried.getText().toString()));
        goodgameplay.word_picked = settings.getString("game_word_picked", goodgameplay.word_picked);
        set_questionmark_size(goodgameplay.word_picked.length());

        String word = settings.getString("game_word_progress", goodgameplay.set_questionmarks(false));
        questionmarks.setText(word);
        show_picture_at_win(word);
        show_word_at_loss();
    }

    // starts gameplay immediately on first launch
    private void first_time() {
        if (!settings.getBoolean("first_time", false)) {
            reset();
            editor = settings.edit();
            editor.putBoolean("first_time", true);
            editor.commit();
        }
    }
}
