package com.project.android.marrigelaws;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 13zqn on 2017/5/27.
 */

public class MAdapter extends RecyclerView.Adapter<MAdapter.MyHolder> {
    private List<Item>mData;
    private Context mContext;
    public MAdapter(Context context, List<Item>data){
        this.mContext = context;
        this.mData = data;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.lawyerservewaitcheck_list,null);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Item item = mData.get(position);
        holder.bindLawyerItem(item);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView mTime;
        private ImageView mPic;
        private TextView mContext;
        private Item mItem;
        public MyHolder(View itemView) {
            super(itemView);
            mTime = (TextView)itemView.findViewById(R.id.time_import);
            mPic=(ImageView)itemView.findViewById(R.id.pic_finish_or_not);
            mContext = (TextView)itemView.findViewById(R.id.lawyer_context);
        }
        public void bindLawyerItem(Item item){
            mItem = item;
            mPic.setImageResource(mItem.getPicture());
            mTime.setText(mItem.getDate());
            mContext.setText(mItem.getContexts());
        }
    }

}
