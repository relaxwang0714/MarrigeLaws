package com.project.android.marrigelaws;

import java.util.UUID;

/**
 * Created by 13zqn on 2017/5/19.
 */

class News {
    private UUID mId;
    private int mPicture;
    private String mTitle;
    private String mNews_context;

    public News(int picture,String title,String news_context){
        mId = UUID.randomUUID();
        mPicture = picture;
        mTitle = title;
        mNews_context = news_context;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public int getPicture() {
        return mPicture;
    }

    public void setPicture(int picture) {
        mPicture = picture;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getNews_context() {
        return mNews_context;
    }

    public void setNews_context(String news_context) {
        mNews_context = news_context;
    }
}
