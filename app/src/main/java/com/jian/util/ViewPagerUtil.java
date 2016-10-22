package com.jian.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.example.jian.bingdu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jian on 2016/9/2.
 */
public class ViewPagerUtil {
    public static  List<ImageView> initIndicator(Context context,View v,int size){
        List<ImageView> imageViews = new ArrayList<>();
        ImageView imgView;
        for (int i = 0; i < size; i++) {
            imgView = new ImageView(context);
            LinearLayout.LayoutParams params_linear = new LinearLayout.LayoutParams(20, 20);
            params_linear.setMargins(7, 10, 7, 10);
            imgView.setLayoutParams(params_linear);
            imageViews.add(imgView);
            if (i == 0) { // 初始化第一个为选中状态
                imageViews.get(i).setBackgroundResource(R.mipmap.indicate);
            } else {
                imageViews.get(i).setBackgroundResource(R.mipmap.indicated);
            }
            ((ViewGroup) v).addView(imageViews.get(i));
        }

        return  imageViews;
    }
}
