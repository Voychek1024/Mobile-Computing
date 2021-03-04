package com.shu_mc_03.word_town.utils;

import android.content.Context;
import android.widget.Toast;


public class ToastUtil {

    /**短吐司**/
    public static void shortShow(Context context,String msg){
        if(StringUtil.isNotEmpty(msg)){
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
        }
    }
}
