package com.qmusic.qmusic.User;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;

public class Save_pref {
    SharedPreferences pref;
    Context context;

    public Save_pref(Context context) {
        pref = context.getSharedPreferences("pref_user", Context.MODE_PRIVATE);
        this.context = context;
    }

    public void setPref(boolean status, String name, String email, String numberPhone) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("status", status);
        editor.putString("name", name);
        editor.putString("email", email);
        editor.putString("numberPhone", numberPhone);
        editor.apply();
    }

    public boolean getPref() {
        boolean status = pref.getBoolean("status", false);
        return status;
    }
    public String getName(){
        return pref.getString("name","");
    }
    public String getEmail(){
        return pref.getString("email","");
    }
    public String getNumberPhone(){
        return pref.getString("numberPhone","");
    }

}
