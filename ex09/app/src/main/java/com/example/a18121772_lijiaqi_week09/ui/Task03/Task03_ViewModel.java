package com.example.a18121772_lijiaqi_week09.ui.Task03;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Task03_ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Task03_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is TASK_03");
    }

    public LiveData<String> getText() {
        return mText;
    }
}