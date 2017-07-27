package com.project.android.marrigelaws;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by 13zqn on 2017/5/21.
 */

public class LawyerServe extends AppCompatActivity{
    private Button publish;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publish_question);
        publish = (Button)findViewById(R.id.publish_question_publish);
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LawyerServe.this,LawyerServeWait.class);
                LawyerServe.this.startActivity(intent);
            }
        });
        getSupportActionBar().hide();
    }
}
