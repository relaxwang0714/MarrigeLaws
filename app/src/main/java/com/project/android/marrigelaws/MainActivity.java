package com.project.android.marrigelaws;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("NewApi")
public class MainActivity extends FragmentActivity implements OnClickListener
{
    private ChangeColorIconWithText one;
    private ChangeColorIconWithText two;
    private ChangeColorIconWithText three;
    private List<ChangeColorIconWithText> mTabIndicators = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTabIndicator();
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.main_in);
        if (fragment == null) {
            fragment = new Menu_main_frg();
            fm.beginTransaction().add(R.id.main_in, fragment).commit();
        }
    }

    private void initTabIndicator()
    {
        one = (ChangeColorIconWithText) findViewById(R.id.menu_main);
        two = (ChangeColorIconWithText) findViewById(R.id.menu_check);
        three = (ChangeColorIconWithText) findViewById(R.id.menu_my);

        mTabIndicators.add(one);
        mTabIndicators.add(two);
        mTabIndicators.add(three);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);

        one.setIconAlpha(0.5f);
    }
    public void onClick(View v) {
        resetOtherTabs();
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.main_in);
        switch (v.getId()) {
            case R.id.menu_main:
                mTabIndicators.get(0).setIconAlpha(0.5f);
                if (fragment == null) {
                    fragment = new Menu_main_frg();
                    fm.beginTransaction().add(R.id.main_in, fragment).commit();
                } else {
                    fragment = new Menu_main_frg();
                    fm.beginTransaction().replace(R.id.main_in, fragment).commit();
                }
                break;
            case R.id.menu_check:
                mTabIndicators.get(1).setIconAlpha(0.5f);
                if (fragment == null) {
                    fragment = new Menu_laywer_frg();
                    fm.beginTransaction().add(R.id.main_in, fragment).commit();
                } else {
                    fragment = new Menu_laywer_frg();
                    fm.beginTransaction().replace(R.id.main_in, fragment).commit();
                }
                break;
            case R.id.menu_my:
                mTabIndicators.get(2).setIconAlpha(0.5f);
                if (fragment == null) {
                    fragment = new Menu_msg_frg();
                    fm.beginTransaction().add(R.id.main_in, fragment).commit();
                } else {
                    fragment = new Menu_msg_frg();
                    fm.beginTransaction().replace(R.id.main_in, fragment).commit();
                }
                break;
        }
    }

    /**
     * 重置其他的TabIndicator的颜色
     */
    private void resetOtherTabs() {
        for (int i = 0; i < mTabIndicators.size(); i++) {
            mTabIndicators.get(i).setIconAlpha(0);
        }
    }

}

