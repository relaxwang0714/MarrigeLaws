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

public class FindHistoryFragment extends FragmentActivity {
    private TabLayout mMainHistorySelect;
    private ViewPager mHistoryVP;
    private HistorySelect mHistorySelect;
    private LawyerServeWaitChecks mHistoryQuestion;
    private FragmentPagerAdapter PAdapter; //定义adapter
    private List<Fragment> lists_fragment; //定义要装fragment的列表
    private List<String> lists_title; //tab名称列表
    private ImageButton back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_my_history);
        initHistory();
        back = (ImageButton) findViewById(R.id.menu_my_history_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FindHistoryFragment.this.finish();
            }
        });
    }

    private void initHistory() {

        mMainHistorySelect = (TabLayout)findViewById(R.id.tab_FindHistoryFragment_title);
        mHistoryVP = (ViewPager)findViewById(R.id.vp_FindHistoryFragment_pager);

        mHistorySelect = new HistorySelect();
        mHistoryQuestion = new LawyerServeWaitChecks();

        lists_fragment = new ArrayList<>();
        lists_fragment.add(mHistorySelect);
        lists_fragment.add(mHistoryQuestion);
        lists_title = new ArrayList<>();
        lists_title.add("历史查询");
        lists_title.add("历史提问");

        mMainHistorySelect.setTabMode(TabLayout.MODE_FIXED);
        mMainHistorySelect.addTab(mMainHistorySelect.newTab().setText(lists_title.get(0)));
        mMainHistorySelect.addTab(mMainHistorySelect.newTab().setText(lists_title.get(1)));
        PAdapter = new Find_tab_Adapter(getSupportFragmentManager(),lists_fragment,lists_title);
        mHistoryVP.setAdapter(PAdapter);
        mMainHistorySelect.setupWithViewPager(mHistoryVP);
    }
}
