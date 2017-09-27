package com.example.loadingdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;


public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    private List<Bean> beanList;
    private Context context;

    public MyRecyclerAdapter(Context context, List<Bean> been) {
        this.context = context;
        this.beanList = been;
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    @Override
    public MyRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyRecyclerAdapter.MyViewHolder holder, final int clickPosition) {
        Bean bean = beanList.get(clickPosition);
        holder.tv_name.setText(bean.getName());
        Glide.with(context)
                .load(bean.getImage())
                .crossFade()
                .into(holder.image);


    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView tv_name;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.iv_image);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        }

    }


}
