package com.example.a18121772_lijiaqi_week09.tasks.Task02;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Task02_ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Task02_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Task02: XML PULL Test");
    }

    public LiveData<String> getText() {
        return mText;
    }
}