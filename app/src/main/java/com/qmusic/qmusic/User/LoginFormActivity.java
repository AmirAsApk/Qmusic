package com.qmusic.qmusic.User;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.qmusic.qmusic.R;

import java.util.ArrayList;
import java.util.List;

public class LoginFormActivity extends AppCompatActivity {
    TabLayout tabs;
    ViewPager pager;
    Fragment_Login fragment_login;
    Fragment_register fragment_register;
    List<Fragment> fragmentList;
    List<String>title ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        fragment_login = new Fragment_Login();
        fragment_register = new Fragment_register();
        fragmentList=new ArrayList<>();
        title=new ArrayList<>();
        title.add("ورود");
        title.add("ثبت نام");

        fragmentList.add(fragment_login);
        fragmentList.add(fragment_register);
        tabs = findViewById(R.id.activity_loginForm_tabs);
        tabs.setTabMode(TabLayout.MODE_FIXED);

        pager = findViewById(R.id.activity_loginForm_viewPager);
        pager.setAdapter(new SlidAdapter(getSupportFragmentManager()));

        tabs.setupWithViewPager(pager);
    }

    public class SlidAdapter extends FragmentPagerAdapter {
        public SlidAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return title.get(position);
        }
    }
}