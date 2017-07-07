package com.project.android.marrigelaws;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

/**
 * Created by 13zqn on 2017/5/24.
 */

class LawyerItem {
    private UUID mId;
    private SimpleDateFormat mDate;
    private int mPicture;
    private String mContexts;
    public LawyerItem() {
        mId = UUID.randomUUID();
        getDate();
    }
    public UUID getId(){
        return mId;
    }

    public String getDate() {
//        mDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date();
//        return mDate.format(date);
        Calendar c = Calendar.getInstance();

        String year = String.valueOf(c.get(Calendar.YEAR));
        String month = String.valueOf(c.get(Calendar.MONTH));
        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        String mins = String.valueOf(c.get(Calendar.MINUTE));

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(year + "-" + month + "-" + day + "-" +hour + "-" + mins);
        return stringBuffer.toString();
    }

//    public void setDate(SimpleDateFormat date) {
//        mDate = date;
//    }

//    @Override
//    public String toString() {
//        return getDate().toString();
//    }

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
