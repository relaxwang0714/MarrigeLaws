package com.project.android.marrigelaws;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by 13zqn on 2017/7/5.
 */

public class Menu_msg_frg_Set extends AppCompatActivity{
    private ImageButton back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_my_set);
        getSupportActionBar().hide();
        back = (ImageButton)findViewById(R.id.menu_my_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Menu_msg_frg_Set.this.finish();
            }
        });
    }
}
