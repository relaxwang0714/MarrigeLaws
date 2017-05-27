//package com.project.android.marrigelaws;
//
//import android.content.Context;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//
///**
// * Created by 13zqn on 2017/5/26.
// */
//
//public class LawyerItemLab {
//    private Date mDate;
//    private static LawyerItemLab sLawyerItemLab;
//    private static List<LawyerItem> mItems;
//    public static LawyerItemLab get(Context context){
//        if (sLawyerItemLab == null){
//            sLawyerItemLab = new LawyerItemLab(context);
//        }
//        return sLawyerItemLab;
//    }
//    private LawyerItemLab(Context context){
//        mItems = new ArrayList<>();
//        for (int i = 0;i<50;i++){
//            LawyerItem item = new LawyerItem(getDate(), getPicture(), getContexts());
//            mItems.add(item);
//        }
//    }
//
//    public Date getDate() {
//        return mDate;
//    }
//
//    public void setDate(Date date) {
//        mDate = date;
//    }
//
//    private String getContexts() {
//        String[] names = {"你好", "没事", "没有", "空", "那就好", "好的", "我这就去", "好好", "哈哈", "可能"};
//        return names[(int) Math.floor(Math.random() * 10)];
//    }
//
//    private int getPicture() {
//        int[] portraits = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
//        return portraits[(int) Math.floor(Math.random() * 3)];
//    }
//
//
//    public static List<LawyerItem> getItems(){
//        return mItems;
//    }
//    public LawyerItem getItems(UUID id){
//        for(LawyerItem item:mItems){
//            if(item.getId().equals(id)){
//                return item;
//            }
//        }
//        return null;
//    }
//}
