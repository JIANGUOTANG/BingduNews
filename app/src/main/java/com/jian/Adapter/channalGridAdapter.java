package com.jian.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jian.bingdu.R;
import com.jian.entity.ChannalInfo;

import java.util.List;

/**
 * Created by jian on 2016/10/15.
 */
public class channalGridAdapter extends RecyclerView.Adapter<channalGridAdapter.ViewHolder> {
    private List<ChannalInfo> channalInfos;
    public channalGridAdapter(List<ChannalInfo> channalInfos) {
        this.channalInfos = channalInfos;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_channal, parent, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChannalInfo  channalInfo  =channalInfos.get(position);
        Log.i("jian","1"+channalInfo.getTitle());
        holder.image.setImageResource(channalInfo.getImageId());
        holder.title.setText(channalInfo.getTitle());
    }
    @Override
    public int getItemCount() {
        return channalInfos.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.channal_image);
            title = (TextView) itemView.findViewById(R.id.channal_tvName);

        }
    }
}
