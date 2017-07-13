package com.project.android.marrigelaws;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 13zqn on 2017/7/6.
 */

public class HistoryQuestion extends Fragment {
    private SlidingButtonView mListView;
    private List<String> mDatas;
    private SimpleAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_question, container, false);
        mListView = (SlidingButtonView) view.findViewById(R.id.id_listview);
        // 不要直接Arrays.asList
        List<Map<String,Object>> items = new ArrayList<Map<String, Object>>();
        for (int i = 0 ; i<10 ; i++){
            Map<String,Object> item = new HashMap<String,Object>();
            item.put("title","历史"+(i+1));
            item.put("time",getDate());
            items.add(item);
        }
        mAdapter = new SimpleAdapter(getActivity(),items, R.layout.history_list,new String[]{"title","time"},new int[]{R.id.history_title,R.id.history_time});
        mListView = (SlidingButtonView) view.findViewById(R.id.id_listview);
        mListView.setAdapter(mAdapter);

        mListView.setDelButtonClickListener(new SlidingButtonView.DelButtonClickListener()
        {
            @Override
            public void clickHappend(final int position)
            {
                Toast.makeText(getActivity(), position + " : " + mAdapter.getItem(position), Toast.LENGTH_SHORT).show();
//                mAdapter.re(mAdapter.getItem(position));
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Toast.makeText(getActivity(), position + " : " + mAdapter.getItem(position), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    public String getDate() {
        Calendar c = Calendar.getInstance();

        String year = String.valueOf(c.get(Calendar.YEAR));
        String month = String.valueOf(c.get(Calendar.MONTH));
        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append( year + "." + month + "." + day );
        return stringBuffer.toString();
    }

}

