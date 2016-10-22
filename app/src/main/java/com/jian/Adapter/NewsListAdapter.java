package com.jian.Adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.elyeproj.loaderviewlibrary.LoaderImageView;
import com.elyeproj.loaderviewlibrary.LoaderTextView;
import com.example.jian.bingdu.R;
import com.jian.entity.NewsInfo;
import com.jian.util.MyUtil;
import com.jian.util.ViewPagerUtil;
import com.listener.MyListener;
import org.xutils.x;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by jian on 2016/10/15.
 */
public class NewsListAdapter extends BaseRecyclerAdapter<NewsInfo,NewsListAdapter.ViewHolder> {
    private static Context mContext;
    private List<NewsInfo> myDatas;
    static boolean isHerad = false;
    private CallBackListener callBackListener;

    public CallBackListener getCallBackListener() {
        return callBackListener;
    }

    public void setCallBackListener(CallBackListener callBackListener) {
        this.callBackListener = callBackListener;
    }

    public interface CallBackListener{
        Void starActivity(View v);
    }
    public NewsListAdapter(List<NewsInfo> myDatas, Context context) {
        super(myDatas);
        this.myDatas = myDatas;
        this.mContext = context;
    }
    @Override
    public void onBaseBindViewHolder(final ViewHolder holder, NewsInfo newsInfo) {
        if(newsInfo.getType()==MyUtil.TYPE_BODY) {
            new thread(holder, newsInfo).start();


        }
    }
    @Override
    public ViewHolder onBaseCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if(viewType == MyUtil.TYPE_HEARD){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.headerviewpager, parent, false);
            isHerad = true;

        }
        else{
             v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_newimage, parent, false);
            isHerad = false;

         }
        return new ViewHolder(v);
    }
    @Override
    public void refresh() {

    }
    @Override
    public void addMord() {

    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
            public LoaderTextView title, detail;
            public LoaderImageView image;
            public LinearLayout news_parent;
            public ViewHolder(View itemView) {
                super(itemView);
                if(!isHerad) {
                    title = (LoaderTextView) itemView.findViewById(R.id.new_tvTitle);
                    news_parent = (LinearLayout) itemView.findViewById(R.id.news_parent);
                    detail = (LoaderTextView) itemView.findViewById(R.id.new_tvDetail);
                    image = (LoaderImageView) itemView.findViewById(R.id.new_img);
                }
                else{
                    initViewpager(viewpagerurls,itemView);
                }
            }
    }
    private static String[] viewpagerurls = new String[]{
            "http://imgsrc.baidu.com/forum/w%3D580/sign=cf1932563f87e9504217f3642039531b/4607ae345982b2b7a9bb045134adcbef77099bc6.jpg",
            "http://imgsrc.baidu.com/forum/w%3D580/sign=688afea6768da9774e2f86238050f872/6095247f9e2f07080a221795ec24b899a801f2c7.jpg",
            "http://imgsrc.baidu.com/forum/w=580/sign=b69d45d7402309f7e76fad1a420f0c39/1ef84659252dd42ad1e351e8053b5bb5cbeab8da.jpg",
            "http://dn-kdt-img.qbox.me/upload_files/2015/07/23/Fq7JWThrcb7zoTGR_2Cj35jMw8sa.jpg",
            "http://s8.sinaimg.cn/mw690/002t3vGlzy6I4xxbYTtf7"};
    public static void initViewpager(String[] urls,View v) {
        ViewPager view_pager;//viewpager，在实验室和设备界面都有用到
        LayoutInflater inflater;//viewPager下面的指示点的父布局。
        View item;//viewPager下面的指示点
         MyAdapter adapter;//viewpager的适配器
        List<View> list;//存放viewPager下面的指示点
         View indicate;
        indicate = v.findViewById(R.id.indicator);//viewpager下面的点
        inflater = LayoutInflater.from(mContext);
        view_pager = (ViewPager) v.findViewById(R.id.view_pager);
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
        List<ImageView> indicaors = ViewPagerUtil.initIndicator(mContext, indicate, urls.length);
        view_pager.addOnPageChangeListener(new MyListener(urls.length, indicaors));
    }
    class handler extends Handler {
        private ViewHolder holder;
        private NewsInfo NewsInfo;
        public handler(Looper looper, ViewHolder holder, NewsInfo NewsInfo) {
            super(looper);
            this.holder = holder;
            this.NewsInfo = NewsInfo;
        }
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    holder.title.setText(NewsInfo.getName());
                    holder.detail.setText(NewsInfo.getDetail());
                    if (NewsInfo.getImgUrl() != null) {
                        x.image().bind(holder.image, NewsInfo.getImgUrl());
                    }
                    break;
            }
        }
    }
    @Override
    public int getItemViewType(int position) {
        return myDatas.get(position).getType();
    }
    private int WAIT_DURATION = 1500;
    class thread extends Thread implements Runnable{
        private ViewHolder holder;
        private NewsInfo NewsInfo;
        public thread(ViewHolder holder, NewsInfo NewsInfo) {
            this.holder = holder;
            this.NewsInfo = NewsInfo;
        }
        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(WAIT_DURATION);
                handler handler = new handler(mContext.getMainLooper(),holder,NewsInfo);
                handler.sendEmptyMessage(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
