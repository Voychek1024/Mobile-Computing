package com.shu_mc_03.word_town.ui.collections;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CollectionsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CollectionsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("WRONG ANSWER COLLECTIONS");
    }

    public LiveData<String> getText() {
        return mText;
    }
}