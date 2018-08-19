package com.example.rathana.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.rathana.recyclerviewdemo.adapter.GridLayoutAdapter;

import java.util.ArrayList;
import java.util.List;

public class RVGridLayoutManagerActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    GridLayoutAdapter adapter;
    List<String> data=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rvgrid_layout_manager);
        recyclerView=findViewById(R.id.rvGridLayout);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,
                3,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter=new GridLayoutAdapter(data);
        recyclerView.setAdapter(adapter);
        updateData();

    }

    private void updateData() {
        List<String> data=new ArrayList<>();
        for (int i=0;i<50;i++){
            data.add("Grid Layout "+i);
        }

        adapter.setData(data);
    }
}
