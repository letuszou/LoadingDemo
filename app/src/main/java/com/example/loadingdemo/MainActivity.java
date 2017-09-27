package com.example.loadingdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.loadinglibrary.LoadUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private RecyclerView recycler_list;
    private String url = "http://ocf5ueq1o.bkt.clouddn.com/page_loading.json";
    private MyRecyclerAdapter recyclerAdapter;
    private Gson gson;
    private List<Bean> beanList;
    private LoadUtils loadUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        init();
        initData();
    }

    private void initView() {
        recycler_list = (RecyclerView) findViewById(R.id.recycler_list);
    }

    private void init() {
        gson = new Gson();
        beanList = new ArrayList<Bean>();
        recycler_list.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new MyRecyclerAdapter(MainActivity.this, beanList);
        recycler_list.setAdapter(recyclerAdapter);

        loadUtils = new LoadUtils(MainActivity.this,recycler_list,recyclerAdapter) {
            @Override
            public void onRefresh() {
                initData();
            }
        };
    }

    private void initData() {
        OkGo.<String>get(url).tag(this).execute(new StringCallback() {
                                                    @Override
                                                    public void onSuccess(Response<String> response) {
                                                        List<Bean> been = gson.fromJson(response.body(), new TypeToken<List<Bean>>() {
                                                        }.getType());
                                                        beanList.clear();
                                                        for (int i = 0; i < been.size(); i++) {
                                                            beanList.add(been.get(i));
                                                        }
                                                        recyclerAdapter.notifyDataSetChanged();
                                                        loadUtils.viewFinish();
                                                    }
                                                    @Override
                                                    public void onError(Response<String> response) {
                                                        super.onError(response);
                                                        loadUtils.viewFinish();
                                                    }
                                                }
        );
    }

}
