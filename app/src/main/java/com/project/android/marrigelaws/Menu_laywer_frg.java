package com.project.android.marrigelaws;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by 13zqn on 2017/5/16.
 */

public class Menu_laywer_frg extends Fragment implements View.OnClickListener{
    private RelativeLayout mSmart;
    private RelativeLayout mLawItem;
    private RelativeLayout mBookCheck;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.menu_check,container,false);
        mSmart = (RelativeLayout) v.findViewById(R.id.menu_check_smart);
        mLawItem = (RelativeLayout) v.findViewById(R.id.menu_check_law_item);
        mBookCheck = (RelativeLayout) v.findViewById(R.id.menu_check_book);

        mSmart.setOnClickListener(this);
        mLawItem.setOnClickListener(this);
        mBookCheck.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menu_check_smart:
                Intent intent1 = new Intent();
                intent1.setClass(getActivity(),Smart_Robot.class);
                getActivity().startActivity(intent1);
                break;
            case R.id.menu_check_law_item:
                Intent intent2 = new Intent();
                intent2.setClass(getActivity(),FatiaoSearch.class);
                getActivity().startActivity(intent2);
                break;
            case R.id.menu_check_book:
                Intent intent3 = new Intent();
                intent3.setClass(getActivity(),WenShuSpinner.class);
                getActivity().startActivity(intent3);
                break;
        }
    }
}
