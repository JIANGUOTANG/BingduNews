package com.jian.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jian.bingdu.R;
import org.xutils.x;
import java.util.List;

/**
 * Created by jian on 2016/9/2.
 */
public class MyAdapter extends PagerAdapter {
    private ImageView image;
    private List<View> mList;
    private String[] urls;
    public MyAdapter() {

    }
    public MyAdapter(List<View> list, String[] urls) {
        this.urls = urls;
        mList = list;
    }
    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return urls.length;
    }
    /**
     * Remove a page for the given position.
     * 滑动过后就销毁 ，销毁当前页的前一个的前一个的页！
     * instantiateItem(View container, int position)
     * This method was deprecated in API level . Use instantiateItem(ViewGroup, int)
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        container.removeView(mList.get(position));
    }
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == arg1;
    }
    /**
     * Create the page for the given position.
     */
    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        View view = mList.get(position);
        image = ((ImageView) view.findViewById(R.id.image));
        x.image().bind(image,urls[position]);
        container.removeView(mList.get(position));
        container.addView(mList.get(position));
        // adapter.notifyDataSetChanged();
        return mList.get(position);
    }
}