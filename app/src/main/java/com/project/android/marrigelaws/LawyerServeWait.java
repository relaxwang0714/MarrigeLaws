package com.project.android.marrigelaws;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 13zqn on 2017/5/22.
 */

public class LawyerServeWait extends AppCompatActivity {
    private TextView mCheck;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publish_question_wait);
        mCheck = (TextView)findViewById(R.id.check_lawyer);
        mCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(LawyerServeWait.this,LawyerServeWaitChecks.class);
//                LawyerServeWait.this.startActivity(intent);
            }
        });
    }
}
