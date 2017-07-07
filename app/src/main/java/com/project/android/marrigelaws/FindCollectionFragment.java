package com.project.android.marrigelaws;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13zqn on 2017/7/6.
 */

public class FindCollectionFragment extends FragmentActivity {
    private TabLayout tab_FindFragment_title; //定义TabLayout
    private ViewPager vp_FindFragment_pager; //定义viewPager
    private FragmentPagerAdapter fAdapter; //定义adapter
    private List<Fragment> list_fragment; //定义要装fragment的列表
    private List<String> list_title; //tab名称列表
    private Find_wenshuCollectionFragment wenshuCollectionFragment; //文书收藏fragment
    private Find_fatiaoCollectionFragment fatiaoCollectionFragment; //法条收藏fragment
    private ImageButton mback;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_my_collection);
        initControls();
        mback = (ImageButton) findViewById(R.id.menu_my_collection_back);
        mback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FindCollectionFragment.this.finish();
            }
        });
    }
    private void initControls() {
        tab_FindFragment_title = (TabLayout)findViewById(R.id.tab_FindFragment_title);
        vp_FindFragment_pager = (ViewPager)findViewById(R.id.vp_FindFragment_pager);
        //初始化各fragment
        wenshuCollectionFragment = new Find_wenshuCollectionFragment();
        fatiaoCollectionFragment = new Find_fatiaoCollectionFragment();
        //将fragment装进列表中
        list_fragment = new ArrayList<>();
        list_fragment.add(wenshuCollectionFragment);
        list_fragment.add(fatiaoCollectionFragment);
        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        list_title = new ArrayList<>();
        list_title.add("文书收藏");
        list_title.add("法条收藏");
        //设置TabLayout的模式
        tab_FindFragment_title.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(0)));
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(1)));
        fAdapter = new Find_tab_Adapter(getSupportFragmentManager(),list_fragment,list_title);
        //viewpager加载adapter
        vp_FindFragment_pager.setAdapter(fAdapter);
        //tab_FindFragment_title.setViewPager(vp_FindFragment_pager);
        //TabLayout加载viewpager
        tab_FindFragment_title.setupWithViewPager(vp_FindFragment_pager);
        //tab_FindFragment_title.set
    }
}
