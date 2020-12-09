package com.example.a18121772_lijiaqi_week09.tasks.Task01;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Task01_ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Task01_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Task01: SharedPreferences Test");
    }

    public LiveData<String> getText() {
        return mText;
    }
}