package com.project.android.marrigelaws;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13zqn on 2017/7/6.
 */

public class Find_fatiaoCollectionFragment extends Fragment{
    private SwipeRecyclerView recyclerView;
    private List<Item> data;
    private RecyclerViewAdapter adapter;
    private int pagerSize = 10;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rcy_swipe,container,false);
        recyclerView = (SwipeRecyclerView) view.findViewById(R.id.swipeRecyclerView);

        //set color
        recyclerView.getSwipeRefreshLayout()
                .setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        recyclerView.getRecyclerView().setLayoutManager(new LinearLayoutManager(getActivity()));
        addData();
        adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        recyclerView.setOnLoadListener(new SwipeRecyclerView.OnLoadListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        data.clear();
                        for (int i = 0; i < pagerSize; i++) {
                            Item item = new Item();
                            item.setTitle("法条收藏" + (i + 1));
                            data.add(item);
                        }

                        recyclerView.complete();
                        adapter.notifyDataSetChanged();

                    }
                }, 1000);

            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < pagerSize; i++) {
                            Item item = new Item();
                            item.setTitle("法条收藏" + (i + 11));
                            data.add(item);
                        }

                        if(data.size() > 10){
                            recyclerView.onNoMore("-- the end --");
                        }else {
                            recyclerView.stopLoadingMore();
                            adapter.notifyDataSetChanged();
                        }
                    }
                }, 1000);
            }
        });
        recyclerView.setRefreshing(true);
        return view;
    }
    private class RecyclerViewAdapter extends RecyclerView.Adapter<ItemViewHolder> {

        @Override
        public int getItemCount() {
            return data == null ? 0 : data.size();
        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.rcy_list, parent, false);
            return new ItemViewHolder(view);
        }


        @Override
        public void onBindViewHolder(final ItemViewHolder holder, final int position) {

            Item item = data.get(position);
            holder.bindLawyerItem(item);

            //for test item click listener
//                    holder.tv.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Toast.makeText(getActivity(), "快来咬我啊，我是" + position + "号", Toast.LENGTH_SHORT).show();
//                        }
//                    });
        }
    }


    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView mTime;
        private Item mItem;
        private TextView mTitle;
        public ItemViewHolder(View view) {
            super(view);
            mTime = (TextView)itemView.findViewById(R.id.rcy_time);
            mTitle = (TextView) itemView.findViewById(R.id.rcy_title);
        }
        public void bindLawyerItem(Item item){
            mItem = item;
            mTime.setText(mItem.getDateO());
            mTitle.setText(mItem.getTitle());
        }
    }
    private void addData() {
        data = new ArrayList<>();

        for (int i = 0 ; i<10 ; i++){
            Item item = new Item();
            item.setTitle("法条收藏" + (i + 1));
            data.add(item);
        }
    }
}
