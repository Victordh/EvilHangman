package com.example.bubbles.evilhangman;

import android.content.res.Resources;
import android.view.View;

import com.example.bubbles.evilhangman.GameplayActivity;

import java.util.Random;

public class Gameplay {

    public String word_picked;

    // picks a word randomly from the dictionary
    public String random_word(){
        Integer random_number = new Random().nextInt(GameplayActivity.words.length);
        String[] array = GameplayActivity.words;
        word_picked = array[random_number];
        String the_word_was = word_picked;
        return word_picked;
    }

    // puts up the correct amount of questionmarks
    public String set_questionmarks(){
        String temp = "";
        for (Integer i = 0; i < word_picked.length(); i++) {
            temp += "? ";
        }
        return temp;
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
            return qmarks.toString();
        }
        return questionmarks;
    }
                /*int location = picked_word.indexOf(first_letter);
                String new_word = word.substring(0,(location * 2))+ first_letter + word.substring(location * 2 + 1);
                the_word.setText(new_word);

                // removes the letter from the computer's word
                char[] picked_word_chars = picked_word.toCharArray();
                picked_word_chars[location] = '?';
                picked_word = String.valueOf(picked_word_chars);

                // check if the entire word has been revealed
                if(questionmarks.matches("^[\pL\pN]+$")) {
                    guess_button.setVisibility(View.INVISIBLE);
            }
        }


        // removes one guess chance if letter not in word
        else {*/
            // remove 1 from guesses left
            // change picture accordingly

            /*Integer amount = Integer.parseInt(guesses_left.getText().toString().substring(9, 10));
            amount -= 1;
            guesses_left.setText("You have " + amount + " wrong guesses left!");
            switch (amount) {
                case 5:
                    hangman6.setVisibility(View.INVISIBLE);
                    hangman5.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    hangman5.setVisibility(View.INVISIBLE);
                    hangman4.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    hangman4.setVisibility(View.INVISIBLE);
                    hangman3.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    hangman3.setVisibility(View.INVISIBLE);
                    hangman2.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    hangman2.setVisibility(View.INVISIBLE);
                    hangman1.setVisibility(View.VISIBLE);
                    break;
                case 0:
                    hangman1.setVisibility(View.INVISIBLE);
                    hangman0.setVisibility(View.VISIBLE);
                    guess_button.setVisibility(View.INVISIBLE);
                    break;
            }*/
}
