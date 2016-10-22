package com.example.jian.bingdu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import com.jian.Fragment.ADFragment;
import com.jian.Fragment.NewsFragment;
import com.listener.FragmetnCallBackListener;

public class MainActivity extends AppCompatActivity implements FragmetnCallBackListener {
    private static Fragment[] mFragments;//fragment数组
    private static FragmentTransaction fragmentTransaction;
    private static FragmentManager fragmentManager;
    private ADFragment adFragment;
    private NewsFragment newsFragment;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initView();
        initData();
        initEvent();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.main);//设置右上角的填充菜单
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.about) {
                    Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        return true;
    }

    private void initEvent() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                fragmentTransaction = fragmentManager.beginTransaction()
                        .hide(mFragments[0]).hide(mFragments[1]);
                switch (checkedId) {
                    case R.id.chioce:
                        fragmentTransaction.show(mFragments[0]).commit();
                        break;
                    case R.id.find:
                        fragmentTransaction.show(mFragments[1]).commit();
                        break;
                }
            }
        });
    }

    private void startActivitytoAb(View v) {
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, v, "dateilImage");
        Intent intent = new Intent(MainActivity.this, DateilActivity.class);
        startActivity(intent, optionsCompat.toBundle());
    }

    private void initData() {
        mFragments = new Fragment[2];
        newsFragment.setFragmetnCallBackListener(this);
        mFragments[0] = newsFragment;
        mFragments[1] = adFragment;
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction()
                .hide(mFragments[0]).hide(mFragments[1]);
        // 显示主页
        fragmentTransaction.show(mFragments[0]).commit();
    }

    private void initView() {
        newsFragment = (NewsFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_news);
        adFragment = (ADFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_ad);
        radioGroup = (RadioGroup) findViewById(R.id.main_radioGroup);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void startActivity(View v) {
        startActivitytoAb(v);
    }
}
