package com.project.android.marrigelaws;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

/**
 * Created by 13zqn on 2017/5/24.
 */

class Item {
    private UUID mId;
    private SimpleDateFormat mDate;
    private int mPicture;
    private String mContexts;
    private String mTitle;
    public Item() {
        mId = UUID.randomUUID();
        getDate();
    }
    public UUID getId(){
        return mId;
    }

    public String getDate() {
        Calendar c = Calendar.getInstance();

        String year = String.valueOf(c.get(Calendar.YEAR));
        String month = String.valueOf(c.get(Calendar.MONTH));
        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        String mins = String.valueOf(c.get(Calendar.MINUTE));

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(year + "." + month + "." + day + " " +hour + ":" + mins);
        return stringBuffer.toString();
    }
    public String getDateO() {
        Calendar c = Calendar.getInstance();

        String year = String.valueOf(c.get(Calendar.YEAR));
        String month = String.valueOf(c.get(Calendar.MONTH));
        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append( year + "." + month + "." + day );
        return stringBuffer.toString();
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

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
