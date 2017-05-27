package com.project.android.marrigelaws;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 13zqn on 2017/5/26.
 */

public class LawyerServeWaitChecks extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wait_fragment);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.wait_frg);
        if (fragment == null) {
            fragment = new LawyerServeWaitCheck();
            fm.beginTransaction().add(R.id.wait_frg, fragment).commit();
        }
    }
}
