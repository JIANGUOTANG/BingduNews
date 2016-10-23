package com.example.jian.bingdu;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
/**
 * Created by jian on 2016/10/22.
 */
public class WelcomeActivity extends AppCompatActivity {
    public static final  int WELCOMETOMAIN = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomactivity);
        initView();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Handler.obtainMessage(WELCOMETOMAIN).sendToTarget();
            }
        });
    }

    private void initView() {

    }
    Handler Handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if(msg.what == WELCOMETOMAIN){
                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
            }
            return false;
        }
    });

}
