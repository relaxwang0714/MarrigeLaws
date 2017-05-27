package com.project.android.marrigelaws;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 13zqn on 2017/5/24.
 */

public class LawyerServeWaitCheck extends Fragment {
    private RecyclerView mrcy;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lawyerservewaitcheck,container,false);
        mrcy = (RecyclerView)v.findViewById(R.id.rcy);
        mrcy.setLayoutManager(new LinearLayoutManager(getActivity()));
        return v;
    }
    //寻找视图
    private class LawyerCheckHolder extends RecyclerView.ViewHolder{
        private TextView mTime;
        private ImageView mPic;
        private TextView mContext;
        private LawyerItem mItem;
        public LawyerCheckHolder(View itemView) {
            super(itemView);
            mTime = (TextView)itemView.findViewById(R.id.time_import);
            mPic=(ImageView)itemView.findViewById(R.id.pic_finish_or_not);
            mContext = (TextView)itemView.findViewById(R.id.lawyer_context);
        }
        //绑定视图
        public void bindLawyerItem(LawyerItem item){
            mItem = item;
            mPic.setImageResource(mItem.getPicture());
            mTime.setText(mItem.getDate().toString());
            mContext.setText(mItem.getContexts());
        }
    }
    private class LawyerCheckAdapter extends RecyclerView.Adapter<LawyerCheckHolder>{
        private List<LawyerItem> mItems;
        public LawyerCheckAdapter(List<LawyerItem> items){
            mItems = items;
        }
        @Override
        public LawyerCheckHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.lawyerservewaitcheck_list,null);
            return new LawyerCheckHolder(view);
        }

        @Override
        public void onBindViewHolder(LawyerCheckHolder holder, int position) {
            LawyerItem mItem = mItems.get(position);
            holder.bindLawyerItem(mItem);
        }


        @Override
        public int getItemCount() {
            return mItems.size();
        }
    }
}
