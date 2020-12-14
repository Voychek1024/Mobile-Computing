package com.shu_mc_03.illusion.ui.share;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Share_ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Share_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("BANQUET");
    }

    public LiveData<String> getText() {
        return mText;
    }
}