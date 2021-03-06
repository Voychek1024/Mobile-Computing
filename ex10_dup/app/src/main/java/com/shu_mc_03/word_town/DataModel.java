package com.shu_mc_03.word_town;

public class DataModel {
    private final String word;
    private final String explanation;
    private boolean starred;
    private final int db_index;
    private final int db_mode;

    public DataModel(String wd, String exp, boolean star, int mode, int index) {
        word = wd;
        explanation = exp;
        starred = star;
        db_index = index;
        db_mode = mode;
    }
    public String getWord() {
        return word;
    }
    public String getExplanation() {
        return explanation;
    }
    public void setStarred() {
        this.starred = !this.starred;
    }
    public boolean getStarred() {
        return this.starred;
    }
    public int getDb_mode() {
        return db_mode;
    }
    public int getDb_index() {
        return db_index;
    }
}
