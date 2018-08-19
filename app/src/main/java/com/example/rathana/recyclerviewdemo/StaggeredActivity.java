package com.example.rathana.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.rathana.recyclerviewdemo.adapter.StaggerdGridLayoutAdapter;

import java.util.ArrayList;
import java.util.List;

public class StaggeredActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    StaggerdGridLayoutAdapter adapter;
    List<Integer> data=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered);

        recyclerView=findViewById(R.id.rvStaggeredGrid);
        StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter=new StaggerdGridLayoutAdapter(this.data);
        recyclerView.setAdapter(adapter);

        updateData();

    }

    private void updateData() {
        List<Integer> data=new ArrayList<>();
        for(int i=0; i<10;i++){
            data.add(R.drawable.download);
            data.add(R.drawable.inspiring);
            data.add(R.drawable.stacks_image);
        }

        adapter.setData(data);
    }
}
