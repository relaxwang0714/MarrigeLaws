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
        String[] names = new String []{
                "《婚姻法》第四十七条规定：“离婚时，一方隐藏、转移、变卖、毁损夫妻共同财产，或伪造债务企图侵占另一方财产的，分割夫妻共...",
                "妻同为一体，家暴是法律明文禁止的，但若是配偶不具有刑事责任能力，伤害了另一方，另一方提起损害赔偿诉讼的，法院是不予支...",
                "可以协商，若是协商不成，法院会根据哪一方能给孩子更好的经济条件、教育资源、感情陪护等因素综合判决……",
                "抚养权的归属问题是离婚案件中一个重头。许多当事人为了争夺抚养权也是使尽办法，出尽感情牌。但相当一部分女当事人争取抚养... ",
                "生活中，夫妻一方以自己的名义借贷，而另一方有时候是知情的，有时候是不知情的，那么离婚时以一方名义借贷的是否属于夫..."
        };
        String[] title = new String[]{
                "离婚时隐藏转移财产的认定和分割",
                "最高人民法院公报：精神病人伤害了配偶，配偶提起损害赔偿诉讼的，法院是否支持？",
                "离婚后子女的抚养归属应遵守哪些规则？",
                "抚养权的归属竟然不是看谁钱多",
                "最高院最新批复：婚姻存续期间一方以个人名义所负的债务是夫妻共同债务吗?"
        };
        int[] picture = new int[]{
                R.drawable.newa,
                R.drawable.newb,
                R.drawable.newc,
                R.drawable.newd,
                R.drawable.newe
                };

        for (int i = 0;i<5;i++){
            News news = new News(picture[i],title[i],names[i]);
            mNewses.add(news);
        }
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
