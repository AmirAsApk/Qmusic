package com.qmusic.qmusic.settings;

import android.content.Context;
import android.content.SharedPreferences;

public class Pref_Dark_Mode {
    SharedPreferences pref;
    Context context;

    public Pref_Dark_Mode(Context context) {
        pref = context.getSharedPreferences("pref_user", Context.MODE_PRIVATE);
        this.context = context;
    }

    public void setPref(boolean status) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("dark_mode", status);
        editor.apply();
    }

    public boolean getPref() {
        return pref.getBoolean("dark_mode",false);
    }
}
