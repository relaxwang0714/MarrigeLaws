
package com.project.android.marrigelaws;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.project.android.marrigelaws.Spinner.SpinerAdapter;
import com.project.android.marrigelaws.Spinner.SpinerPopWindow;

import java.util.ArrayList;
import java.util.List;

public class WenShuSpinner extends Activity implements OnClickListener,SpinerAdapter.IOnItemSelectListener{

    private List<String>  mListTypeA = new ArrayList<String>();  //类型列表
    private TextView mTViewA;
    private SpinerAdapter mAdapterA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.wenshuspinner);
        mTViewA = (TextView) findViewById(R.id.tv_value1);

        //初始化数据
        String[] names = getResources().getStringArray(R.array.wenshu_style);
        for(int i = 0; i < names.length; i++){
            mListTypeA.add(names[i]);
        }
        mAdapterA = new SpinerAdapter(this,mListTypeA);
        mAdapterA.refreshData(mListTypeA,0);

        //显示第一条数据
        //mTView.setText(names[0]);

        //初始化PopWindow
        mSpinerPopWindow = new SpinerPopWindow(this);
        mSpinerPopWindow.setAdatper(mAdapterA);
        mSpinerPopWindow.setItemListener(this);


    }


    //设置PopWindow
    private SpinerPopWindow mSpinerPopWindow;
    private void showSpinWindowA(){
        Log.e("", "showSpinWindow");
        mSpinerPopWindow.setWidth(mTViewA.getWidth());
        mSpinerPopWindow.showAsDropDown(mTViewA);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_value1_lay:
                showSpinWindowA();
                break;
            case R.id.wenshu_checks:
                Intent intent = new Intent();
                intent.setClass(WenShuSpinner.this,WenshuCheck.class);
                WenShuSpinner.this.startActivity(intent);
        }
    }


    /* (non-Javadoc)
     * @see org.gaochun.adapter.SpinerAdapter.IOnItemSelectListener#onItemClick(int)
     */
    @Override
    public void onItemClick(int pos) {
        // TODO Auto-generated method stub
        if (pos >= 0 && pos <= mListTypeA.size()){
            String value = mListTypeA.get(pos);
            mTViewA.setText(value.toString());
        }
    }

}
