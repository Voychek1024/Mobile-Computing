package com.shu_mc_03.word_town.ui.game;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GameViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GameViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("GAME FIELD");
    }

    public LiveData<String> getText() {
        return mText;
    }
}