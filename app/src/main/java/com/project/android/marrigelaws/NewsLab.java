package com.project.android.marrigelaws;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by 13zqn on 2017/5/19.
 */

public class NewsLab {
    private static NewsLab sNewsLab;
    private static List<News> mNewses;
    public static NewsLab get(Context context){
        if (sNewsLab == null){
            sNewsLab = new NewsLab(context);
        }
        return sNewsLab;
    }
    private NewsLab(Context context){
        mNewses = new ArrayList<>();
        for (int i = 0;i<5;i++){
            News news = new News(getPicture(), getTitle(), getMainContext());
            mNewses.add(news);
        }
    }

    private String getMainContext() {
        String[] names = {"你好", "没事", "没有", "空", "那就好", "好的", "我这就去", "好好", "哈哈", "可能"};
        return names[(int) Math.floor(Math.random() * 10)];
    }

    private String getTitle() {
        String[] names = {"杀杀", "大柱子", "叶子", "菜菜", "土豪金", "门受", "音受", "思雨", "李月月", "吃剩"};
        return names[(int) Math.floor(Math.random() * 10)];
    }

    private int getPicture() {
        int[] portraits = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
        return portraits[(int) Math.floor(Math.random() * 3)];
    }


    public static List<News> getNewses(){
        return mNewses;
    }
    public News getNews(UUID id){
        for(News news:mNewses){
            if(news.getId().equals(id)){
                return news;
            }
        }
        return null;
    }

}
