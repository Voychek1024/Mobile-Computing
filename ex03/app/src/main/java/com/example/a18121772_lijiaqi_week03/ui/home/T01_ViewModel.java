package com.example.a18121772_lijiaqi_week03.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class T01_ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public T01_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Task01: 文本传递");
    }

    public LiveData<String> getText() {
        return mText;
    }
}