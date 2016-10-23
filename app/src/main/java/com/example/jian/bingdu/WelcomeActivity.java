package com.example.jian.bingdu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.jian.Adapter.MyViewpagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jian on 2016/10/22.
 */
public class WelcomeActivity extends AppCompatActivity {
    public static final  int WELCOMETOMAIN = 1;
    private int[] iamgid = {R.mipmap.imag1,R.mipmap.imag2,R.mipmap.imag3,R.mipmap.imag4};
    private ViewPager viewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomactivity);
        initView();

    }
    private List<View> listViews = null;
    private void initView() {
        listViews = new ArrayList<>();
        for(int i = 0;i<4;i++){
            View v = LayoutInflater.from(this).inflate(R.layout.item_viewpager,null);
            ImageView ima = (ImageView) v.findViewById(R.id.view_pager_iamgeview);
            ima.setBackgroundResource(iamgid[i]);
            listViews.add(v);
        }
        MyViewpagerAdapter myViewpagerAdapter = new MyViewpagerAdapter(listViews);
       viewPager = (ViewPager) findViewById(R.id.welcome_viewpager);
        viewPager.setAdapter(myViewpagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==3){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Handler.obtainMessage(WELCOMETOMAIN).sendToTarget();
                        }
                    }).start();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    Handler Handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if(msg.what == WELCOMETOMAIN){
                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
            return false;
        }
    });

}
