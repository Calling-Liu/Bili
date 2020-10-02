package com.example.bilibili01.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bilibili01.R;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
    private String[] mData;
    //自定义holder
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ImageView imageView;
        public TextView textView1;
        public ImageView imageView1;
        public MyViewHolder(View view){
            super(view);
            textView  = view.findViewById(R.id.text01);
//            imageView = view.findViewById(R.id.image01);
//            textView1  = view.findViewById(R.id.text02);
//            imageView1 = view.findViewById(R.id.image02);
        }
    }
    public RecycleAdapter(String[] mData){
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_row, parent, false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(mData[position]);
//        holder.textView1.setText("第二个");
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
