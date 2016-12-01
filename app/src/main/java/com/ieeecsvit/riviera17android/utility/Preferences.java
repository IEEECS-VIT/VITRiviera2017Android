package com.ieeecsvit.riviera17android.utility;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Karishnu Poddar on 28/08/2016.
 */
public class Preferences {
    public static void setPrefs(String key, String value, Context context){
        SharedPreferences sharedpreferences = context.getSharedPreferences("RivieraPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getPrefs(String key, Context context){
        SharedPreferences sharedpreferences = context.getSharedPreferences("RivieraPrefs", Context.MODE_PRIVATE);
        return sharedpreferences.getString(key, "notfound");
    }

    public static void deletePrefs(Context context){
        SharedPreferences sharedpreferences = context.getSharedPreferences("RivieraPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.apply();
    }
}
