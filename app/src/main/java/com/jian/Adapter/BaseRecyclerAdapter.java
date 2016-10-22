package com.jian.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by jian on 2016/9/12.
 */
public abstract class BaseRecyclerAdapter<T,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private int itemCount;
    private boolean isHaveMore = false;//是否还更多的数据
    private List<T> myDatas;//目前该显示的数据
    private List<T> AllDatas;//全部的数据
    public BaseRecyclerAdapter(List<T> myDatas) {
        showitem(myDatas);
        itemCount = myDatas.size();
        AllDatas = myDatas;
    }
    /**
     *
     * @param myDatas 需要显示的信息
     * 决定是显示部分还是全部信息
     */
    private void showitem(List<T> myDatas) {
        if (itemCount > 11) {
            this.myDatas = myDatas.subList(0, 9);
            isHaveMore = true;
        } else {
            this.myDatas = myDatas;
            isHaveMore = false;
        }
    }
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return onBaseCreateViewHolder(parent, viewType);
    }
    @Override
    public void onBindViewHolder(VH holder, int position) {
        T t = myDatas.get(position);
        onBaseBindViewHolder(holder, t);
    }
    public abstract void onBaseBindViewHolder(VH holder, T t);
    public abstract VH onBaseCreateViewHolder(ViewGroup parent, int viewType);
    @Override
    public int getItemCount() {
        return myDatas.size();
    }
    public abstract void refresh();
    public abstract void addMord();
    //模拟刷新
    public void refreshData(T t){
        myDatas.add(1,t);
        notifyDataSetChanged();
    }
    public void add() {
        if (isHaveMore) {
            if (itemCount - myDatas.size() >= 5) {
                myDatas.addAll(AllDatas.subList(myDatas.size()-1,myDatas.size()+4));//加载五条
            }
            else{
                myDatas=AllDatas;//加载全部
            }
            notifyDataSetChanged();
        }
    }
}
