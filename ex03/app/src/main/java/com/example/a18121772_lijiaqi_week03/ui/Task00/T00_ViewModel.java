package com.example.a18121772_lijiaqi_week03.ui.Task00;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class T00_ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public T00_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Task00: 自行设计布局");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
