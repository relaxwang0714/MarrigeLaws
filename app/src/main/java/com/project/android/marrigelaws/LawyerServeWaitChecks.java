package com.project.android.marrigelaws;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13zqn on 2017/5/26.
 */

public class LawyerServeWaitChecks extends AppCompatActivity {
    private RecyclerView mrcy;
    private List<Item>data;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lawyerservewaitcheck);
        //设置recycleview
        mrcy = (RecyclerView)findViewById(R.id.rcy);
        //设置固定大小
        mrcy.setHasFixedSize(true);
        //创建线性布局
        mLinearLayoutManager = new LinearLayoutManager(this);
        //垂直方向
        mLinearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //给rcy设置布局管理器
        mrcy.setLayoutManager(mLinearLayoutManager);
        data = new ArrayList<>();

        for (int i = 0 ; i<5 ; i++){
            Item item = new Item();
            item.setContexts(getContexts());
            item.setPicture(getPicture());
            data.add(item);
        }

        MAdapter adapter = new MAdapter(this,data);
        mrcy.setAdapter(adapter);
    }

    public int getPicture() {
        int[]pic ={R.drawable.a,R.drawable.b,R.drawable.c};
        return pic[(int) (Math.random()*3)];
    }

    public String getContexts() {
        String[] mContexts = {"杀杀", "大柱子", "叶子", "菜菜", "土豪金", "门受", "音受", "思雨", "李月月", "吃剩"};
        return mContexts[(int) (Math.random() * 10)];
    }
}
