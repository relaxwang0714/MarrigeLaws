package com.project.android.marrigelaws;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by 13zqn on 2017/7/4.
 */

public class ChangeColorIconWithText extends View {
    private Canvas mCanvas;//Canvas画布
    private Bitmap mBitmap;//保存像素的Bitmap( 位图文件)
    private Paint mPaint;
    //Paint类保存有关如何绘制几何图形，文本和位图的样式和颜色信息。
    private int mColor = 0XFF07B7C4;//颜色
    private float mAlpha=0f;//透明度
    private Bitmap mIconBitmap;//图标
    private Rect mIconRect;//限制绘制icon的范围
    /** Android.graphics.Rect类，这个类同android.graphics.RectF很相似，不同的地方是Rect类的坐标是用整形表示的，而RectF的坐标是用单精度浮点型表示的。
     * RectF 这个类包含一个矩形的四个单精度浮点坐标。矩形通过上下左右4个边的坐标来表示一个矩形。
     * RectF一共有四个构造方法：RectF（）构造一个无参的矩形
     *RectF（float left,float top,float right,float bottom）构造一个指定了4个参数的矩形
     *RectF(Rect F r) 根据指定的RectF对象来构造一个RectF对象(对象的左边坐标不变)
     *RectF(Rect r) 根据给定的Rect对象来构造一个RectF对象
     */
    private String mText = "主页";//底部文字
    private int mTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12,
            getResources().getDisplayMetrics());//字体大小
    private Rect mTextBound;//文本绑定
    private Paint mTextPaint;//文本的绘制信息

    public ChangeColorIconWithText(Context context) {
        super(context);
    }

    public ChangeColorIconWithText(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ChangeColorIconWithText); // 获取设置的图标
        //获取自定义属性值的方式一般情况分为两种：styleable组 和 直接获取attr属性
        //这里获取的属性用的styleable组
        int n = a.getIndexCount();

        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.ChangeColorIconWithText_icon:
                    BitmapDrawable drawable = (BitmapDrawable) a.getDrawable(attr);
                    mIconBitmap = drawable.getBitmap();//图片标题
                    break;
                case R.styleable.ChangeColorIconWithText_color:
                    mColor = a.getColor(attr,0x07B7C4);
                    // 默认颜色设置为珊瑚蓝
                    break;
                case R.styleable.ChangeColorIconWithText_text:
                    mText = a.getString(attr);//文字
                    break;
                case R.styleable.ChangeColorIconWithText_text_size:
                    mTextSize = (int) a.getDimension(attr,
                            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    // 默认设置为12sp，TypeValue也可以把sp转化为px
                    break;
            }

        }

        a.recycle();//回收资源

        mTextBound = new Rect(); //设置画笔
        mTextPaint = new Paint();//新建画笔对象
        mTextPaint.setTextSize(mTextSize);//设置画笔字体大小
        mTextBound = new Rect();//？文本绑定
        mTextPaint.setColor(0xff07B7C4);//设置画笔颜色
        mTextPaint.getTextBounds(mText, 0, mText.length(), mTextBound); //设置画笔相关区域

    }

    /**
     * 获取自定义属性的值
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public ChangeColorIconWithText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int iconWidth = Math.min(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(),
                getMeasuredHeight() - getPaddingTop() - getPaddingBottom() - mTextBound.height());// 得到绘制icon的宽
        int left = getMeasuredWidth() / 2 - iconWidth / 2;
        int top = getMeasuredHeight() / 2 - (mTextBound.height() + iconWidth) / 2;
        mIconRect = new Rect(left, top, left + iconWidth, top + iconWidth);// 设置icon的绘制范围
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mIconBitmap, null, mIconRect, null);  //绘制背景,全屏
        int alpha = (int) Math.ceil(255 * mAlpha);
        setupTargetBitmap(alpha); // 内存去准备mBitmap , setAlpha , 纯色 ，xfermode ， 图标
        drawSourceText(canvas, alpha);// 绘制原文本
        drawTargetText(canvas, alpha);//绘制变色的文本
        canvas.drawBitmap(mBitmap, 0, 0, null);

    }

    /**
     * 绘制变色的文本
     *
     * @param canvas
     * @param alpha
     */
    private void drawTargetText(Canvas canvas, int alpha) {
        mTextPaint.setColor(mColor);//字体颜色
        mTextPaint.setAlpha(alpha);//字体透明度
        int x = getMeasuredWidth() / 2 - mTextBound.width() / 2;
        int y = mIconRect.bottom + mTextBound.height();
        canvas.drawText(mText, x, y, mTextPaint);

    }

    /**
     * 绘制原文本
     *
     * @param canvas
     * @param alpha
     */
    private void drawSourceText(Canvas canvas, int alpha) {
        mTextPaint.setColor(0xFF696969);//底部字体的颜色
        mTextPaint.setAlpha(255 - alpha);//底部字体的透明度
        int x = getMeasuredWidth() / 2 - mTextBound.width() / 2;
        int y = mIconRect.bottom + mTextBound.height();
        canvas.drawText(mText, x, y, mTextPaint);

    }

    /**
     * 在内存中绘制可变色的Icon
     */
    private void setupTargetBitmap(int alpha) {
        mBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setAlpha(alpha);
        mCanvas.drawRect(mIconRect, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setAlpha(255);
        mCanvas.drawBitmap(mIconBitmap, null, mIconRect, mPaint);
    }

    private static final String INSTANCE_STATUS = "instance_status";
    private static final String STATUS_ALPHA = "status_alpha";

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATUS, super.onSaveInstanceState());
        bundle.putFloat(STATUS_ALPHA, mAlpha);
        return bundle;
    }
    /*关于onSaveInstanceState ()，是在函数里面保存一些View有用的数据到一个Parcelable对象并返回。
     在Activity的onSaveInstanceState(Bundle outState)中调用View的onSaveInstanceState ()，返回Parcelable对象.
     当系统调用Activity的的onRestoreInstanceState(Bundle savedInstanceState)时，同过Bundle的getParcelable方法得到Parcelable对象，
     然后把该Parcelable对象传给View的onRestoreInstanceState (Parcelable state)。在的View的onRestoreInstanceState中从Parcelable读取保存的数据以便View使用。
    */
    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        /**  bundle的用法：
         /* Bundle相当于Map类,就是一个映射,用Bundle绑定数据,便于数据处理
         它主要作用于Activity之间的数据传递*/
        if (state instanceof Bundle) {//instanceof是Java的一个二元操作符，是Java的保留关键字。它的作用是测试它左边的对象是否是它右边的类的实例，返回boolean类型的数据。
            Bundle bundle = (Bundle) state;
            mAlpha = bundle.getFloat(STATUS_ALPHA);
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATUS));
            return;
        }
        super.onRestoreInstanceState(state);
    }
    //设置icon透明度的方法
    public void setIconAlpha(float alpha) {
        this.mAlpha = alpha;
        invalidateView();
    }

    /**
     * 重绘
     */
    // Android中的Looper类，是用来封装消息循环和消息队列的一个类，用于在android线程中进行消息处理。handler其实可以看做是一个工具类，用来向消息队列中插入消息的。
    private void invalidateView() {
        //Looper.myLooper()：获取当前进程的looper对象，类似的 Looper.getMainLooper() 用于获取主线程的Looper对象。
        if (Looper.getMainLooper() == Looper.myLooper()) {
            invalidate();//刷新
        } else {
            postInvalidate();
        }
        //android中实现view的更新有两组方法，一组是invalidate，另一组是postInvalidate，其中前者是在UI线程自身中使用，而后者在非UI线程中使用。
    }

}
