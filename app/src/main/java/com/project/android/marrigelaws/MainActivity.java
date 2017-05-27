package com.project.android.marrigelaws;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * 程序主入口
 * @author liuyazhuang
 *
 */
public class MainActivity extends FragmentActivity {
    private RelativeLayout main;
    private RelativeLayout lawyer;
    private RelativeLayout msg;
//    private ViewPager mViewPaper;
//    private List<ImageView> images;
//    private List<View> dots;
//    private int currentItem;
//    //记录上一次点的位置
//    private int oldPosition = 0;
//    //存放图片的id
//    private int[] imageIds = new int[]{
//            R.drawable.a,
//            R.drawable.b,
//            R.drawable.c,
//            R.drawable.d,
//            R.drawable.e
//    };
//    //存放图片的标题
//    private String[]  titles = new String[]{
//            "Macbook1",
//            "Macbook2",
//            "Macbook3",
//            "Macbook4",
//            "Macbook5"
//    };
//    private TextView title;
//    private ViewPagerAdapter adapter;
//    private ScheduledExecutorService scheduledExecutorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main = (RelativeLayout)findViewById(R.id.menu_main);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.main_in);
        if (fragment == null) {
            fragment = new Menu_main_frg();
            fm.beginTransaction().add(R.id.main_in, fragment).commit();
        }
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                Fragment fragment = fm.findFragmentById(R.id.main_in);
                if (fragment == null) {
                    fragment = new Menu_main_frg();
                    fm.beginTransaction().add(R.id.main_in, fragment).commit();
                }else {
                    fragment = new Menu_main_frg();
                    fm.beginTransaction().replace(R.id.main_in, fragment).commit();
                }
            }
        });
        lawyer = (RelativeLayout)findViewById(R.id.menu_lawyer);
        lawyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                Fragment fragment = fm.findFragmentById(R.id.main_in);
                if (fragment == null) {
                    fragment = new Menu_laywer_frg();
                    fm.beginTransaction().add(R.id.main_in, fragment).commit();
                }else {
                    fragment = new Menu_laywer_frg();
                    fm.beginTransaction().replace(R.id.main_in, fragment).commit();
                }
            }
        });
        msg = (RelativeLayout)findViewById(R.id.menu_msg);
        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                Fragment fragment = fm.findFragmentById(R.id.main_in);
                if (fragment == null) {
                    fragment = new Menu_msg_frg();
                    fm.beginTransaction().add(R.id.main_in, fragment).commit();
                }else {
                    fragment = new Menu_msg_frg();
                    fm.beginTransaction().replace(R.id.main_in, fragment).commit();
                }
            }
        });
        //Fragment fragment = new DommyFragment();
//        Bundle bundle = new Bundle();
//        bundle.putInt("Num",tab.getPosition()+1);
//        fragment.setArguments(bundle);
//
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.container,fragment);
//        transaction.commit();
//        mViewPaper = (ViewPager)findViewById(R.id.vp);
//
//        //显示的图片
//        images = new ArrayList<ImageView>();
//        for(int i = 0; i < imageIds.le    ngth; i++){
//            ImageView imageView = new ImageView(this);
//            imageView.setBackgroundResource(imageIds[i]);
//            images.add(imageView);
//        }
//        //显示的小点
//        dots = new ArrayList<View>();
//        dots.add(findViewById(R.id.dot_0));
//        dots.add(findViewById(R.id.dot_1));
//        dots.add(findViewById(R.id.dot_2));
//        dots.add(findViewById(R.id.dot_3));
//        dots.add(findViewById(R.id.dot_4));
//
//        title = (TextView) findViewById(R.id.title);
//        title.setText(titles[0]);
//
//        adapter = new ViewPagerAdapter();
//        mViewPaper.setAdapter(adapter);
//
//        Animation animation = new Animation() {
//            @Override
//            protected Animation clone() throws CloneNotSupportedException {
//                setFeatureDrawableAlpha(0,100);
//
//                return super.clone();
//            }
//        };
//        mViewPaper.setAnimation(animation);
//
//        mViewPaper.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//            //这里写的是页面改变，小圆点也改变
//            @Override
//            public void onPageSelected(int position) {
//                title.setText(titles[position]);
//                dots.get(position).setBackgroundResource(R.drawable.dot_focused);
//                dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
//
//                oldPosition = position;
//                currentItem = position;
//
//            }
//
//            @Override
//            public void onPageScrolled(int arg0, float arg1, int arg2) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int arg0) {
//
//            }
//        });
    }

//    /**
//     * 自定义Adapter
//     * @author liuyazhuang
//     *
//     */
//    private class ViewPagerAdapter extends PagerAdapter{
//
//        @Override
//        public int getCount() {
//            return images.size();
//        }
//
//        @Override
//        public boolean isViewFromObject(View arg0, Object arg1) {
//            return arg0 == arg1;
//        }
//
//        @Override
//        public void destroyItem(ViewGroup view, int position, Object object) {
//            // TODO Auto-generated method stub
////			super.destroyItem(container, position, object);
////			view.removeView(view.getChildAt(position));
////			view.removeViewAt(position);
//            view.removeView(images.get(position));
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup view, int position) {
//            // TODO Auto-generated method stub
//            view.addView(images.get(position));
//            return images.get(position);
//        }
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    //线程
//    @Override
//    protected void onStart() {
//        // TODO Auto-generated method stub
//        super.onStart();
//        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//        scheduledExecutorService.scheduleWithFixedDelay(
//                new ViewPageTask(),
//                2,
//                2,
//                TimeUnit.SECONDS);
//    }
//
//
//    private class ViewPageTask implements Runnable{
//
//        @Override
//        public void run() {
//            currentItem = (currentItem + 1) % imageIds.length;
//            mHandler.sendEmptyMessage(0);
//        }
//    }
//
//    /**
//     * 接收子线程传递过来的数据
//     */
//    private Handler mHandler = new Handler(){
//        public void handleMessage(android.os.Message msg) {
//            mViewPaper.setCurrentItem(currentItem);
//        };
//    };
//    @Override
//    protected void onStop() {
//        // TODO Auto-generated method stub
//        super.onStop();
//    }

}

