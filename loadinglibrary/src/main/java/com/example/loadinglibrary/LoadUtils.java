package com.example.loadinglibrary;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class LoadUtils {

    private Activity activity;
    private ImageView iv_page_loading;
    private LinearLayout ll_no_data_again;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private View view;

    public LoadUtils(Activity activity, RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        this.activity = activity;
        this.adapter = adapter;
        this.recyclerView = recyclerView;
        initConfig();
    }

    public LoadUtils(View view, RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        this.view = view;
        this.adapter = adapter;
        this.recyclerView = recyclerView;
        initConfig();
    }

    private void initConfig() {
        if(activity == null){
            TextView tv_no_data_again = (TextView) view.findViewById(R.id.tv_no_data_again);
            iv_page_loading = (ImageView) view.findViewById(R.id.iv_page_loading);
            ll_no_data_again = (LinearLayout) view.findViewById(R.id.ll_no_data_again);
            tv_no_data_again.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewLoading();
                    onRefresh();
                }
            });
        }else{
            TextView tv_no_data_again = (TextView) activity.findViewById(R.id.tv_no_data_again);
            iv_page_loading = (ImageView) activity.findViewById(R.id.iv_page_loading);
            ll_no_data_again = (LinearLayout) activity.findViewById(R.id.ll_no_data_again);
            tv_no_data_again.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewLoading();
                    onRefresh();
                }
            });
        }

        viewLoading();
    }

    public abstract void onRefresh();

    private void viewLoading() {
        ll_no_data_again.setVisibility(View.GONE);
        iv_page_loading.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    private void viewError() {
        ll_no_data_again.setVisibility(View.VISIBLE);
        iv_page_loading.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
    }


    public void viewFinish() {
        int itemCount = adapter.getItemCount();
        if (itemCount > 0) {
            ll_no_data_again.setVisibility(View.GONE);
            iv_page_loading.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            viewError();
        }

    }


}
