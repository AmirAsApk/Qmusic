package com.qmusic.qmusic.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.qmusic.qmusic.R;
import com.qmusic.qmusic.User.Save_pref;

public class Setting extends AppCompatActivity implements View.OnClickListener {
    EditText et_name, et_email;
    Button btn_save;
    Switch swh_black;
    Pref_Dark_Mode pref_dark_mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        pref_dark_mode = new Pref_Dark_Mode(getApplicationContext());

        et_name = findViewById(R.id.setting_et_name);
        et_email = findViewById(R.id.setting_et_email);
        Save_pref pref = new Save_pref(getApplicationContext());
        if (pref.getPref()) {
            et_name.setText(pref.getName());
            et_email.setText(pref.getEmail());
        }
        swh_black = findViewById(R.id.activity_setting_halat_black);
        swh_black.setChecked(pref_dark_mode.getPref());
        btn_save = findViewById(R.id.activity_setting_btn_save);
        btn_save.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == btn_save.getId()) {
            pref_dark_mode.setPref(swh_black.isChecked());
        }
        finish();
    }
}