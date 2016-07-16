package com.android_api;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android_api.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecyclerViewActivity extends Activity {
    @Bind(R.id.recycler)
    RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<String> contents;
    private LinearLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);
        setData();
    }

    private void setData() {
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        contents = new ArrayList<String>();
        for(int i=0;i<50;i++){
            contents.add(i+"");
        }

        adapter = new MyAdapter(this,contents);
        recyclerView.setAdapter(adapter);
    }


}
