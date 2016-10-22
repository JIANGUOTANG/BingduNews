package com.example.jian.bingdu;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by jian on 2016/10/16.
 */

public class AboutActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setupToolbar();

    }
}
