package edu.logintegra.datamodelingdemo.demo;

public class WordHelper {

    private final String word;
    private final String bigWord;

    public WordHelper(String word, String bigWord) {
        this.word = word;
        this.bigWord = bigWord;
    }

    public String getBigWord() {
        return bigWord;
    }

    public String getWord() {
        return word;
    }
}
