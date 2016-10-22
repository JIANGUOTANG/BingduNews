package com.example.jian.bingdu;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.jian.Adapter.MyAdapter;
import com.jian.util.VersionUtil;
import com.jian.util.ViewPagerUtil;
import com.listener.MyListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jian on 2016/9/5.
 * 可以很好的做到代码重用
 */
public class BaseActivity extends AppCompatActivity {
    private ViewPager view_pager;//viewpager，在实验室和设备界面都有用到
    private LayoutInflater inflater;//viewPager下面的指示点的父布局。
    private View item;//viewPager下面的指示点
    private MyAdapter adapter;//viewpager的适配器
    List<View> list;//存放viewPager下面的指示点
    View v;

    /**
     * 初始化toolbar
     */
    void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
 void initViewpager(String[] urls) {
        v = findViewById(R.id.indicator);//viewpager下面的点
        inflater = LayoutInflater.from(this);
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        list = new ArrayList<>();
        /**
         * 创建多个item （每一条viewPager都是一个item）
         * 从服务器获取完数据（如文章标题、url地址） 后，再设置适配器
         */
        for (int i = 0; i < urls.length; i++) {
            item = inflater.inflate(R.layout.indicator, null);
            list.add(item);
        }
        //创建适配器， 把组装完的组件传递进去
        adapter = new MyAdapter(list, urls);
        view_pager.setAdapter(adapter);
        //绑定动作监听器：如翻页的动画
        List<ImageView> indicaors = ViewPagerUtil.initIndicator(this, v, urls.length);
        view_pager.addOnPageChangeListener(new MyListener(urls.length, indicaors));
    }
    @Override
    public boolean onSupportNavigateUp() {//返回按钮
        onBackPressed();
        return true;
    }
    //进入动画,Explode效果
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    void EnterTransitionslide() {//进入的动画
        if (VersionUtil.isMaterial()) {
            Explode enterTransition = new Explode();
            enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
            getWindow().setEnterTransition(enterTransition);
        }
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
     void buildEnterTransition()  {
//        if (VersionUtil.isMaterial()) {
//            Slide enterTransition = new Slide();
//            enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
//            enterTransition.setSlideEdge(Gravity.RIGHT);
//            getWindow().setEnterTransition(enterTransition);
//        }
    }
    //点击键盘外部收回键盘
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }
    public  boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
