package com.project.android.marrigelaws;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 13zqn on 2017/7/5.
 */

public class Menu_msg_frg_Set extends AppCompatActivity implements View.OnClickListener{
    private ImageButton back;
    private TextView mClear;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_my_set);
        getSupportActionBar().hide();
        back = (ImageButton)findViewById(R.id.menu_my_back);
        back.setOnClickListener(this);
        mClear = (TextView) findViewById(R.id.menu_check_book_txt1);
        mClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menu_my_back:
                Menu_msg_frg_Set.this.finish();
                break;
            case R.id.menu_check_book_txt1:
                Toast.makeText(this, "已清除234kb缓存", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
