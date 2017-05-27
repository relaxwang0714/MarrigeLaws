package com.project.android.marrigelaws;

import java.util.Date;

/**
 * Created by 13zqn on 2017/5/24.
 */

class LawyerItem {
    private Date mDate;
    private int mPicture;
    private String mContexts;
    public void LawyerItem(Date date,int picture,String contexts){
        this.mContexts = contexts;
        this.mDate = date;
        this.mPicture = picture;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public int getPicture() {
        return mPicture;
    }

    public void setPicture(int picture) {
        this.mPicture = picture;
    }

    public String getContexts() {
        return mContexts;
    }

    public void setContexts(String contexts) {
        mContexts = contexts;
    }
}
