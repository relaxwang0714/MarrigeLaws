package com.project.android.marrigelaws;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by 13zqn on 2017/7/26.
 */

public class FatiaoCheck extends AppCompatActivity{
    private SwipeRecyclerView recyclerView;
    private List<Item> data;
    private RecyclerViewAdapter adapter;
    private int pagerSize = 10;
    private MyItemClickListener mItemClickListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rcy_swipe);
        getSupportActionBar().hide();
        recyclerView = (SwipeRecyclerView) findViewById(R.id.swipeRecyclerView);

        //set color
        recyclerView.getSwipeRefreshLayout()
                .setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        recyclerView.getRecyclerView().setLayoutManager(new LinearLayoutManager(this));
        addData();
        adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setItemClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(FatiaoCheck.this, "点击了"+position, Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setOnLoadListener(new SwipeRecyclerView.OnLoadListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        data.clear();
                        for (int i = 0; i < pagerSize; i++) {
                            Item item = new Item();
                            item.setTitle("法条查询" + (i + 1));
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
                            item.setTitle("法条查询" + (i + 11));
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
    }

    private class RecyclerViewAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        @Override
        public int getItemCount() {
            return data == null ? 0 : data.size();
        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.rcy_list, parent, false);
            return new ItemViewHolder(view,mItemClickListener);
        }


        @Override
        public void onBindViewHolder(final ItemViewHolder holder, final int position) {

            Item item = data.get(position);
            holder.bindLawyerItem(item);

//            for test item click listener
//                        holder.itemView.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
////                            startAction(new Intent(Intent.ACTION_VIEW,Uri.parse("www.baidu.com")));
//                            Intent intent = new Intent();
//                            intent.setClass(FatiaoCheck.this,WebNet.class);
//                            FatiaoCheck.this.startActivity(intent);
//                        }
//                    });
        }


        public void setItemClickListener(MyItemClickListener myItemClickListener) {
            mItemClickListener = myItemClickListener;
        }
    }


    private class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTime;
        private Item mItem;
        private TextView mTitle;
        private MyItemClickListener mListener;
        public ItemViewHolder(View itemView, MyItemClickListener myItemClickListener) {
            super(itemView);
//            itemView.setOnClickListener(this);
            mTime = (TextView)itemView.findViewById(R.id.rcy_time);
            mTitle = (TextView) itemView.findViewById(R.id.rcy_title);
            this.mListener = myItemClickListener;
            itemView.setOnClickListener(this);

        }
        public void bindLawyerItem(Item item){
            mItem = item;
            mTime.setText(mItem.getDateO());
            mTitle.setText(mItem.getTitle());
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(v, getPosition());
            }

        }
//
//        @Override
//        public void onClick(View v) {
//            Uri uri = Uri.parse("http://baike.baidu.com/view/753813.htm");
//            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//            startActivity(intent);
//        }
    }
    public interface MyItemClickListener {
        void onItemClick(View view, int position);
    }
//    public void setItemClickListener(MyItemClickListener myItemClickListener) {
//        this.mItemClickListener = myItemClickListener;
//    }

    private void addData() {
        data = new ArrayList<>();

        for (int i = 0 ; i<10 ; i++){
            Item item = new Item();
            item.setTitle("法条查询" + (i + 1));
            data.add(item);
        }
    }
}
