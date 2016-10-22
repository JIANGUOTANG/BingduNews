package com.jian.Fragment;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.elyeproj.loaderviewlibrary.LoaderImageView;
import com.example.jian.bingdu.R;
import com.jian.Adapter.MyAdapter;
import com.jian.Adapter.NewsListAdapter;
import com.jian.entity.NewsInfo;
import com.jian.util.LoadDataScrollController;
import com.jian.util.ViewPagerUtil;
import com.listener.FragmetnCallBackListener;
import com.listener.MyListener;
import com.listener.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by jian on 2016/10/15.
 */
public class NewsFragment extends Fragment {
    private ViewPager view_pager;//viewpager，在实验室和设备界面都有用到
    private LayoutInflater inflater;//viewPager下面的指示点的父布局。
    private View item;//viewPager下面的指示点
    private MyAdapter adapter;//viewpager的适配器
    List<View> list;//存放viewPager下面的指示点
    private View indicate;
    private View v;
    private SwipeRefreshLayout mSwipeRefresh;//拉下刷新用到的布局
    private LoadDataScrollController mController;
    private FragmetnCallBackListener fragmetnCallBackListener;
    public FragmetnCallBackListener getFragmetnCallBackListener() {
        return fragmetnCallBackListener;
    }
    public void setFragmetnCallBackListener(FragmetnCallBackListener fragmetnCallBackListener) {
        this.fragmetnCallBackListener = fragmetnCallBackListener;
    }
    private ProgressDialog pd;
    private RecyclerView mRecyclerView;
    private List<NewsInfo> newsInfos;//新闻列表信息
    private NewsListAdapter mAdapter;//适配器
    //测试用的图片
    String[] urls = new String[]{
            "http://image9.huangye88.cn/2015/05/15/6bf34a6527727869bee5e6b253856703.png",
            "http://img01.taopic.com/141208/318754-14120PZ03520.jpg",
            "http://pic.58pic.com/58pic/15/26/31/91J58PICAGb_1024.jpg",
            "http://files.b2b.cn/product/ProductImages/2011_04/11/11130639629.jpg",
            "http://pic4.zhongsou.com/image/4806f2648c9780aa00d.jpg",
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_choice, null, false);
        initView();//初始化控件
        initData();//初始化数据
        initEnevnt();
        return v;
    }
    private void initEnevnt() {
        /**
         * 设置监听
         */
        mController = new LoadDataScrollController(new LoadDataScrollController.OnRecycleRefreshListener() {
            @Override
            public void refresh() {
                mSwipeRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       mAdapter.refresh();
                        mSwipeRefresh.setRefreshing(false);
                        mController.setLoadDataStatus(false);
                    }
                }, 2000);
            }
            @Override
            public void loadMore() {
                //加载更多的接口回调
                pd = new ProgressDialog(getActivity());
                pd.setTitle("正在加载");
                pd.show();
                mSwipeRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.add();
                        //设置数据加载结束的监听状态
                        mController.setLoadDataStatus(false);
                        pd.dismiss();
                    }
                }, 2000);
            }
        }, 20);
        mRecyclerView.addOnScrollListener(mController);
        mSwipeRefresh.setOnRefreshListener(mController);
    }
    private void initData() {
        newsInfos = new ArrayList<>();
        NewsInfo newsInfo2 = new NewsInfo("新闻标题1", urls[1], "新闻二级标题", "新闻直播间", 100,1);
        newsInfos.add(newsInfo2);
        for (int i = 0, j = urls.length; i < j; i++) {
            NewsInfo newsInfo = new NewsInfo("新闻标题1", urls[i], "新闻二级标题", "新闻直播间", 100,2);
            newsInfos.add(newsInfo);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new NewsListAdapter(newsInfos, getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(position>0)
                fragmetnCallBackListener.startActivity((LoaderImageView)view.findViewById(R.id.new_img));
            }
        }));
//        initViewpager(urls);//初始化viewpager
    }
    private void initView() {
        mRecyclerView = (RecyclerView) v.findViewById(R.id.choice_recyclerView);
        mSwipeRefresh = (SwipeRefreshLayout) v.findViewById(R.id.notification_Swipe);
        mSwipeRefresh.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE);
    }
    private void initViewpager(String[] urls) {
        indicate = v.findViewById(R.id.indicator);//viewpager下面的点
        inflater = LayoutInflater.from(getActivity());
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
        List<ImageView> indicaors = ViewPagerUtil.initIndicator(getActivity(), indicate, urls.length);
        view_pager.addOnPageChangeListener(new MyListener(urls.length, indicaors));
    }
}
