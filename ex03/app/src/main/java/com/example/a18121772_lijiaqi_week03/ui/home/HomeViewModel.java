package com.example.a18121772_lijiaqi_week03.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Task01: 文本传递");
    }

    public LiveData<String> getText() {
        return mText;
    }
}