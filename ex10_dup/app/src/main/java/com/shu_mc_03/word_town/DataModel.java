package com.shu_mc_03.word_town;

import java.util.Locale;

public class DataModel {
    private final String word;
    private final String explanation;

    public DataModel(int id) {
        word = String.format(Locale.ENGLISH, "Word %d Here", id);
        explanation = String.format(Locale.ENGLISH, "Exp %d Here", id);
    }
    public String getWord() {
        return word;
    }
    public String getExplanation() {
        return explanation;
    }

}
