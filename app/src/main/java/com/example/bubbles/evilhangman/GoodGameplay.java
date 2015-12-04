package com.example.bubbles.evilhangman;

import java.util.Random;

public class GoodGameplay extends Gameplay{

    // returns a randomly picked word from the dictionary
    public String random_word(){
        Integer n = new Random().nextInt(GameplayActivity.words.length);
        word_picked = GameplayActivity.words[n];
        return word_picked;
    }

    public String letter_in_word_picked(String letter, String questionmarks) {
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
