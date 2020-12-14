package com.shu_mc_03.illusion.ui.studio;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Studio_ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Studio_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("STUDIO");
    }

    public LiveData<String> getText() {
        return mText;
    }
}