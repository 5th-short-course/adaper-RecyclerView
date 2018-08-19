package com.example.rathana.recyclerviewdemo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rathana.recyclerviewdemo.R;

import java.util.List;

public class GridLayoutAdapter extends RecyclerView.Adapter<GridLayoutAdapter.ViewHolder>{
    public GridLayoutAdapter(List<String> data) {
        this.data = data;
    }

    private static final String TAG = "GridLayoutAdapter";
    List<String> data;
    public void setData(List<String> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
        Log.e(TAG, "setData: "+ this.data.size() );
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.grid_item_layout,parent,false
        );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.textView.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text);
        }
    }

}
