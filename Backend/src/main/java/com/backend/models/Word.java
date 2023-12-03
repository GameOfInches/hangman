/**

 * File: World.java

 * Author: Krasimir Konstantinov

 * Date: 28/11/2023

 */
package com.backend.models;

public class Word {
    private String word;
    private String guessedWord;

    public Word(String word) {
        this.word = word;
        StringBuilder guessedWord = new StringBuilder(word);
        for (int i = 0; i < word.length(); i++) {
            guessedWord.append('_');
        }
        this.guessedWord = guessedWord.toString();
    }

    public String getWord() {

        return word;
    }

    public String getGuessedWord() {

        return guessedWord;
    }

    public void checkLetter(char letter) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                guessedWord = guessedWord.substring(0, i) + letter + guessedWord.substring(i + 1);
            }
        }
    }
}

