package com.shu_mc_03.word_town;

import android.app.Application;

public class AppContext extends Application{

	private static AppContext instance;
	public static synchronized AppContext getInstance() {
		return instance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
	}
}
