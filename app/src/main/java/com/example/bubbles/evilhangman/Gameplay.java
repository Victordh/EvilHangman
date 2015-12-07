package com.example.bubbles.evilhangman;

import android.content.Context;
import android.content.SharedPreferences;

public abstract class Gameplay {

    Context context = GameplayActivity.getContext();
    public static final String PREFERENCES_FILE_NAME = "settings";
    SharedPreferences settings = context.getSharedPreferences(PREFERENCES_FILE_NAME, 0);

    String word_picked;

    // returns a string with the correct amount of questionmarks
    public String set_questionmarks(){
        String temp = "?";
        for (Integer i = 1; i < settings.getInt("word_length_value", 9); i++) {
            temp += " ?";
        }
        return temp;
    }

    // returns the character input if it's a letter and not tried yet
    public String get_letter_input(String letter, String tried) {
        // takes the first character of user input (disregards if no input)
        if (letter.length() != 0) {
            String l = letter.substring(0, 1).toUpperCase();
            // checks if the character is a letter
            if (l.matches("[A-Z]+")) {
                // checks with already tried letters
                if (!tried.contains(l)){
                    return l;
                }
            }
        }
        return "false";
    }

    // returns the word placeholders with all non-guessed letters as ?
    public abstract String letter_in_word_picked(String letter, String questionmarks);

    // returns the current amount of guesses left minus 1
    public int remove_one_guess(String guesses_left) {
        return Integer.parseInt(guesses_left) - 1;
    }

    // returns if the user has revealed the entire word and thus won
    public Boolean word_revealed(String questionmarks) {
        return !questionmarks.contains("?");
    }

    // returns a string of the word with spaces between each letter
    public String the_word_was() {
        String the_word_was = String.valueOf(word_picked.charAt(0));
        for (int i = 1; i < word_picked.length(); i++) {
            the_word_was += " " + word_picked.charAt(i);
        }
        return the_word_was;
    }
}
