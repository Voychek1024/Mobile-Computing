package com.shu_mc_03.word_town;

import java.util.Locale;

public class DataModel {
    private final String word;
    private final String explanation;

    public DataModel(String wd, String exp) {
        word = wd;
        explanation = exp;
    }
    public String getWord() {
        return word;
    }
    public String getExplanation() {
        return explanation;
    }

}
