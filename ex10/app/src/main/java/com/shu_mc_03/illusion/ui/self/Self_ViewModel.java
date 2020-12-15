package com.shu_mc_03.illusion.ui.self;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Self_ViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public Self_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("ABOUT ME");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
