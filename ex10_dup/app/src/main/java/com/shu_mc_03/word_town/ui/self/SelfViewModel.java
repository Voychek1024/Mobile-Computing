package com.shu_mc_03.word_town.ui.self;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SelfViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SelfViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("MYSELF / SCOREBOARD");
    }

    public LiveData<String> getText() {
        return mText;
    }
}