package com.qmusic.qmusic;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.qmusic.qmusic.User.LoginFormActivity;
import com.qmusic.qmusic.User.Save_pref;
import com.qmusic.qmusic.settings.Pref_Dark_Mode;
import com.qmusic.qmusic.settings.Setting;
import com.qmusic.qmusic.ui.category.categoryFragment;
import com.qmusic.qmusic.ui.cinema.cinemaFragment;
import com.qmusic.qmusic.ui.home.HomeFragment;
import com.qmusic.qmusic.ui.artist.PersonFragment;
import com.qmusic.qmusic.ui.search.searchFragment;
import com.ss.bottomnavigation.BottomNavigation;
import com.ss.bottomnavigation.events.OnSelectedItemChangeListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FragmentTransaction transaction;
    ImageView btn_menu;
    DrawerLayout drawerLayout;
    Button btn_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//manage dark mode
        manage_dark_mode();
        btn_menu = findViewById(R.id.activity_main_btn_menu);
        btn_user = findViewById(R.id.activity_main_btn_loginForm);
        btn_menu.setOnClickListener(this);
        drawerLayout = findViewById(R.id.activity_main_drawer_layout);
        BottomNavigation bottomNavigation = findViewById(R.id.activity_main_btn_bottom_nav);
        bottomNavigation.setDefaultItem(2);

        bottomNavigation.setOnSelectedItemChangeListener(new OnSelectedItemChangeListener() {
            @Override
            public void onSelectedItemChanged(int i) {

                switch (i) {
                    case R.id.activity_main_btn_tab_category:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.activity_main_fragment_layout, new categoryFragment());
                        break;
                    case R.id.activity_main_btn_tab_search:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.activity_main_fragment_layout, new searchFragment());
                        break;
                    case R.id.activity_main_btn_tab_home:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.activity_main_fragment_layout, new HomeFragment());
                        break;
                    case R.id.activity_main_btn_tab_person:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.activity_main_fragment_layout, new PersonFragment());
                        break;
                    case R.id.activity_main_btn_tab_cinema:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.activity_main_fragment_layout, new cinemaFragment());
                        break;
                }
                transaction.commit();
            }
        });
    }

    private void manage_dark_mode() {
        if (new Pref_Dark_Mode(getApplicationContext()).getPref()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.activity_main_btn_loginForm) {
            startActivity(new Intent(MainActivity.this, LoginFormActivity.class));
        } else if (view.getId() == btn_menu.getId()) {
            drawerLayout.openDrawer(Gravity.RIGHT);
        } else if (view.getId() == R.id.activity_main_btn_setting) {
            startActivity(new Intent(MainActivity.this, Setting.class));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Save_pref save_pref = new Save_pref(MainActivity.this);
        if (save_pref.getPref()) {
            btn_user.setVisibility(View.GONE);
        } else {
            btn_user.setVisibility(View.VISIBLE);
        }
        manage_dark_mode();
    }
}