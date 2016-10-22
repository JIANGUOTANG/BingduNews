package com.listener;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;

import com.example.jian.bingdu.R;
import com.jian.Adapter.MyAdapter;

import java.util.List;

/**
 * Created by jian on 2016/9/2.
 */
public class MyListener implements ViewPager.OnPageChangeListener {
private int count;
 private  List<ImageView> indicaors;
    public MyListener(int count, List<ImageView> indicaors) {
        this.count = count;
        this.indicaors = indicaors;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // TODO Auto-generated method stub
        if (state == 0) {
            new MyAdapter().notifyDataSetChanged();
        }
    }


    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageSelected(int position) {
        // 改变所有导航的背景图片为：未选中
        Log.i("poisiong",""+position);
        for (int i = 0; i< count ; i++) {
            Log.i("poisiong",""+count);

           indicaors.get(i).setBackgroundResource(R.mipmap.indicated);
        }
        // 改变当前背景图片为：选中
        indicaors.get(position).setBackgroundResource(R.mipmap.indicate);
    }
}
