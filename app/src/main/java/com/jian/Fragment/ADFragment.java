package com.jian.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jian.bingdu.R;
import com.jian.Adapter.MyAdapter;
import com.jian.Adapter.channalGridAdapter;
import com.jian.entity.ChannalInfo;
import com.jian.util.ViewPagerUtil;
import com.listener.MyListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jian on 2016/10/15.
 */
public class ADFragment extends Fragment {
    private ViewPager view_pager;//viewpager,在实验室和设备界面都有用到
    private LayoutInflater inflater;//viewPager下面的指示点的父布局。
    private View item;//viewPager下面的指示点
    private MyAdapter adapter;//viewpager的适配器
    List<View> list;//存放viewPager下面的指示点
    private View indicate;
    private channalGridAdapter mAdapter;//shipqiqi
    private RecyclerView recyclerView;//频道分类
    private View v;//主布局
    private ViewPager viewPager;
    List<ChannalInfo> channalInfons;
    String[] urls = new String[]{
            "http://image9.huangye88.cn/2015/05/15/6bf34a6527727869bee5e6b253856703.png",
            "http://img01.taopic.com/141208/318754-14120PZ03520.jpg",
            "http://pic.58pic.com/58pic/15/26/31/91J58PICAGb_1024.jpg",
            "http://files.b2b.cn/product/ProductImages/2011_04/11/11130639629.jpg",
            "http://pic4.zhongsou.com/image/4806f2648c9780aa00d.jpg",
    };
    int[] imageID = new int[]{R.mipmap.news1, R.mipmap.news2, R.mipmap.news3, R.mipmap.news4, R.mipmap.news5, R.mipmap.news6, R.mipmap.news7, R.mipmap.news8, R.mipmap.news9
            , R.mipmap.news10, R.mipmap.news1, R.mipmap.news12, R.mipmap.news13, R.mipmap.news14, R.mipmap.news15
    };
    String[] Title = {"新闻直播间","生鲜八爪娱","体育最前瞻","财富情报站","逐风车友会","地产风云汇","美食共和国","奇遇新世界","健康养生馆","九号放映厅","游戏人部落","发现号邮轮","生活设计馆","潮流通讯社","极客研究院"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_find, null, false);
        initView();//初始化控件
        initData();//初始化数据
        initViewpager(urls);
        return v;
    }

    private void initData() {
       channalInfons = new ArrayList<>();
       for(int i = 0,j=Title.length;i<j;i++){
           ChannalInfo channalInfo = new ChannalInfo(Title[i],imageID[i]);
           channalInfons.add(channalInfo);
       }
        mAdapter = new channalGridAdapter(channalInfons);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
    }
    private void initView() {
        recyclerView = (RecyclerView) v.findViewById(R.id.channel_RecyclerView);
    }

    private void initViewpager(String[] urls) {
        indicate = v.findViewById(R.id.indicator);//viewpager下面的点
        inflater = LayoutInflater.from(getActivity());
        view_pager = (ViewPager) v.findViewById(R.id.advertiseview_pager);
        list = new ArrayList<>();
        /**
         * 创建多个item （每一条viewPager都是一个item）
         * 从服务器获取完数据（如文章标题、url地址） 后,再设置适配器
         */
        for (int i = 0; i < urls.length; i++) {
            item = inflater.inflate(R.layout.indicator, null);
            list.add(item);
        }
        //创建适配器, 把组装完的组件传递进去
        adapter = new MyAdapter(list, urls);
        view_pager.setAdapter(adapter);
        //绑定动作监听器：如翻页的动画
        List<ImageView> indicaors = ViewPagerUtil.initIndicator(getActivity(), indicate, urls.length);
        view_pager.addOnPageChangeListener(new MyListener(urls.length, indicaors));
    }
}
