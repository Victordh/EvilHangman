package com.example.bubbles.evilhangman;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Random;

public class GoodGameplay extends Gameplay{

    Context context = GameplayActivity.getContext();
    public static final String PREFERENCES_FILE_NAME = "settings";
    SharedPreferences settings =
            context.getSharedPreferences(PREFERENCES_FILE_NAME, 0);

    // returns a randomly picked word from the dictionary
    public String random_word(){
        do {
            Integer n = new Random().nextInt(words.length);
            word_picked = words[n];
        } while(word_picked.length() != settings.getInt("word_length_value",
                9));
        return word_picked;
    }

    // returns the word placeholders with all non-guessed letters as ?
    public String handle_letter(String letter, String questionmarks) {
        // checks if letter is in the_word
        if (word_picked.contains(letter)) {
            // changes the ?(s) to the correct letter
            char[] qmarks = questionmarks.toCharArray();
            char[] word = word_picked.toCharArray();
            for (int i = 0; i < word.length; i++) {
                if (word[i] == letter.toCharArray()[0]) {
                    qmarks[i * 2] = letter.toCharArray()[0];
                }
            }
            questionmarks = new String(qmarks);
        }
        return questionmarks;
    }
}
