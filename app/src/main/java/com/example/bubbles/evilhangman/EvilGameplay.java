package com.example.bubbles.evilhangman;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EvilGameplay extends Gameplay{

    Context context = GameplayActivity.getContext();
    public static final String PREFERENCES_FILE_NAME = "settings";
    SharedPreferences settings =
            context.getSharedPreferences(PREFERENCES_FILE_NAME, 0);

    ArrayList<String> possible_words = new ArrayList<>();
    ArrayList<String> words_left = new ArrayList<>();
    ArrayList<String> check_largest = new ArrayList<>();
    HashMap<String, Integer> occurrences = new HashMap<>();

    // returns the word placeholders with all non-guessed letters as ?
    public String handle_letter(String letter, String questionmarks){
        words_left.clear();
        check_largest.clear();
        occurrences.clear();

        // makes all possible words into ?s to make it easy to find out which
        // group of words is the biggest
        for (int i = 0; i < possible_words.size(); i++) {
            // changes the ?(s) to the correct letter
            char[] qmarks = questionmarks.toCharArray();
            char[] word = possible_words.get(i).toCharArray();
            for (int j = 0; j < settings.getInt("game_word_length", 9); j++) {
                if (word[j] == letter.toCharArray()[0]) {
                    qmarks[j * 2] = letter.toCharArray()[0];
                }
            }
            String q = new String(qmarks);
            check_largest.add(q);
            int k = calculate_occurence(q);
            occurrences.put(q, k);
        }

        // finds the entry with the most occurrences
        Map.Entry<String, Integer> max = null;
        for (Map.Entry<String, Integer> entry : occurrences.entrySet()) {
            if (max == null || entry.getValue().compareTo(max.getValue()) > 0) {
                max = entry;
            }
        }

        String largest = max.getKey();

        for (int i = 0; i < possible_words.size(); i++) {
            if (largest.equals(check_largest.get(i))) {
                words_left.add(possible_words.get(i));
            }
        }

        possible_words.clear();
        possible_words.addAll(words_left);

        return largest;
    }

    // compares strings and returns the amount of duplicates
    private int calculate_occurence(String q) {
        int k = 1;
        for (Map.Entry<String, Integer> entry : occurrences.entrySet()) {
            char[] e = entry.toString().toCharArray();
            char[] qq = q.toCharArray();
            boolean check = true;
            for (int l = 0; l < qq.length; l++) {
                if (e[l] != qq[l]) {
                    check = false;
                }
            }
            if (check) {
                k = entry.getValue();
                k++;
            }
        }
        return k;
    }

    // only use words with the correct length
    public void get_words_with_length() {
        for (String word : words) {
            if (word.length() == settings.getInt("game_word_length", 9)) {
                possible_words.add(word);
            }
        }
    }

    public String get_win_word(String temp) {
        String win_word = "";
        for (int i = 0; i < temp.length(); i++) {
            if (i % 2 == 0) {
                win_word = win_word + temp.charAt(i);
            }
        }
        word_picked = win_word;
        return win_word;
    }
}
