package com.example.a18121772_lijiaqi_week03.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class T02_ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public T02_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Task02: 数值传递");
    }

    public LiveData<String> getText() {
        return mText;
    }
}