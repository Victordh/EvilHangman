package com.example.bubbles.evilhangman;

import java.util.Random;

public class GoodGameplay extends Gameplay{

    // returns a randomly picked word from the dictionary
    public String random_word(){
        Integer n = new Random().nextInt(GameplayActivity.words.length);
        word_picked = GameplayActivity.words[n];
        return word_picked;
    }
}
