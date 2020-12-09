package com.example.a18121772_lijiaqi_week09.ui.Task01;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Task01_ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Task01_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is TASK_01");
    }

    public LiveData<String> getText() {
        return mText;
    }
}