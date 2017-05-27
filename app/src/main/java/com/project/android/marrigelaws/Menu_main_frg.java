package com.project.android.marrigelaws;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by 13zqn on 2017/5/16.
 */

public class Menu_main_frg extends Fragment {
    private ViewPager mViewPaper;
    private RecyclerView mRecyclerView_news;
    private Menu_main_newsAdapter mAdapter;
    private List<ImageView> images;
    private List<View> dots;
    private int currentItem;
    //记录上一次点的位置
    private int oldPosition = 0;
    //存放图片的id
    private int[] imageIds = new int[]{
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e
    };
    //存放图片的标题
    private String[]  titles = new String[]{
            "Macbook1",
            "Macbook2",
            "Macbook3",
            "Macbook4",
            "Macbook5"
    };
    private TextView title;
    private ViewPagerAdapter adapter;
    private LinearLayout checks;
    private ScheduledExecutorService scheduledExecutorService;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.menu_main_frg,container,false);
        mViewPaper = (ViewPager)v.findViewById(R.id.vp);
        checks = (LinearLayout)v.findViewById(R.id.check);
        checks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LawyerServe.class);
                getActivity().startActivity(intent);
//                getActivity().finish();
            }
        });
        mRecyclerView_news = (RecyclerView)v.findViewById(R.id.menu_main_news_recycler_view);
        mRecyclerView_news.setLayoutManager(new LinearLayoutManager(getActivity()));
        //显示的图片
        images = new ArrayList<ImageView>();
        for(int i = 0; i < imageIds.length; i++){
//            ImageView imageView = new ImageView(this);
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }
        //显示的小点
        dots = new ArrayList<View>();
        dots.add(v.findViewById(R.id.dot_0));
        dots.add(v.findViewById(R.id.dot_1));
        dots.add(v.findViewById(R.id.dot_2));
        dots.add(v.findViewById(R.id.dot_3));
        dots.add(v.findViewById(R.id.dot_4));

        title = (TextView) v.findViewById(R.id.title);
        title.setText(titles[0]);

        adapter = new ViewPagerAdapter();
        mViewPaper.setAdapter(adapter);

        Animation animation = new Animation() {
            @Override
            protected Animation clone() throws CloneNotSupportedException {
//                setFeatureDrawableAlpha(0,100);

                return super.clone();
            }
        };
        mViewPaper.setAnimation(animation);

        mViewPaper.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            //这里写的是页面改变，小圆点也改变
            @Override
            public void onPageSelected(int position) {
                title.setText(titles[position]);
                dots.get(position).setBackgroundResource(R.drawable.dot_focused);
                dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);

                oldPosition = position;
                currentItem = position;

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
       updateUI();

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void updateUI() {
        NewsLab newsLab = NewsLab.get(getActivity());
        List<News> newses = NewsLab.getNewses();
        mAdapter = new Menu_main_newsAdapter(newses);
        mRecyclerView_news.setAdapter(mAdapter);
    }

//    private String getMainContext() {
//        String[] names = {"你好", "没事", "没有", "空", "那就好", "好的", "我这就去", "好好", "哈哈", "可能"};
//        return names[(int) Math.floor(Math.random() * 10)];
//    }
//
//    private String getTitle() {
//        String[] names = {"杀杀", "大柱子", "叶子", "菜菜", "土豪金", "门受", "音受", "思雨", "李月月", "吃剩"};
//        return names[(int) Math.floor(Math.random() * 10)];
//    }
//
//    private int getPicture() {
//        int[] portraits = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
//        return portraits[(int) Math.floor(Math.random() * 3)];
//    }

    /**
     * 自定义Adapter
     * @author liuyazhuang
     *
     */
    private class Menu_main_newsHolder extends RecyclerView.ViewHolder{
        private TextView mTextTitle;
        private TextView mTextContext;
        private ImageView mpicture;
        private News mNews;
        //寻找视图
        public Menu_main_newsHolder(View itemView){
            super(itemView);
            mpicture = (ImageView)itemView.findViewById(R.id.news_picture);
            mTextTitle = (TextView)itemView.findViewById(R.id.news_title);
            mTextContext = (TextView)itemView.findViewById(R.id.news_context);
        }
        //绑定视图
        public void bindNews(News news){
            mNews = news;
            mpicture.setImageResource(mNews.getPicture());
            mTextTitle.setText(mNews.getTitle());
            mTextContext.setText(mNews.getNews_context());
        }
    }
    private class Menu_main_newsAdapter extends RecyclerView.Adapter<Menu_main_newsHolder>{
            private List<News> mNewses;
        public Menu_main_newsAdapter(List<News> newses){
            mNewses = newses;
         }
        //实例化定制布局
         @Override
        public Menu_main_newsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            //   View view = layoutInflater.inflate(R.layout.news_list,parent,false);
             View view = layoutInflater.inflate(R.layout.news_list,null);
//            ((ImageView) view.findViewById(R.id.news_picture)).setImageResource(getPicture());
//            ((TextView) view.findViewById(R.id.news_title)).setText(getTitle());
//            ((TextView) view.findViewById(R.id.news_context)).setText(getMainContext());
            return new Menu_main_newsHolder(view);
        }

        @Override
        public void onBindViewHolder(Menu_main_newsHolder holder, int position) {
            News news = mNewses.get(position);
//            holder.mTextTitle.setText(news.gettitle());
//            holder.mTextContext.setText(news.getnews_context());
            //关联adapter和holder
            holder.bindNews(news);
        }

        @Override
        public int getItemCount() {
            return mNewses.size();
        }
    }
    private class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            // TODO Auto-generated method stub
//			super.destroyItem(container, position, object);
//			view.removeView(view.getChildAt(position));
//			view.removeViewAt(position);
            view.removeView(images.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            // TODO Auto-generated method stub
            view.addView(images.get(position));
            return images.get(position);
        }

    }
//Activity中的用法
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//Fragment中应如此使用
@Override
public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.main,menu);
}
    //线程
    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(
                new ViewPageTask(),
                2,
                2,
                TimeUnit.SECONDS);
    }


    private class ViewPageTask implements Runnable{

        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageIds.length;
            mHandler.sendEmptyMessage(0);
        }
    }

    /**
     * 接收子线程传递过来的数据
     */
    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            mViewPaper.setCurrentItem(currentItem);
        };
    };
    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }
}
