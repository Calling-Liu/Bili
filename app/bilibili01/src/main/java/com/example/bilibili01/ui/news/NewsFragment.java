package com.example.bilibili01.ui.news;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.bilibili01.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsFragment extends Fragment {
    //up头像
    private int[] images = new int[]{
            R.mipmap.image01, R.mipmap.image02, R.mipmap.image03, R.mipmap.image04, R.mipmap.image05,
            R.mipmap.image06, R.mipmap.image07
    };
    private String[] names = new String[]{
           "LexBurner", "花少北", "Umika", "南云鸟羽", "凉风Kaze", "T1-Faker",
            "老番茄"
    };
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_news, container, false);
//        ActionBar actionBar = getActivity().getActionBar();
//        actionBar.hide();
//        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //让导航消失
        final Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);
        //       页面跳转设置
        final ImageView imageView =(ImageView) root.findViewById(R.id.fragment1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar.setVisibility(View.VISIBLE);
                Navigation.findNavController(root).navigate(R.id.action_nav_news_to_nav_home);
            }
        });
        ImageView imageView1 =(ImageView) root.findViewById(R.id.fragment2);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.action_nav_news_to_nav_tv);
            }
        });
        ImageView imageView2 =(ImageView) root.findViewById(R.id.fragment4);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.action_nav_news_to_nav_shopping);
            }
        });
        //UP主列表
        GridView gridView = (GridView) root.findViewById(R.id.grid_view_news);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                240*images.length, 200);//180+60=240:一列的宽度
        gridView.setLayoutParams(params);
        gridView.setColumnWidth(180);
        gridView.setNumColumns(images.length);
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setHorizontalSpacing(60);
        BaseAdapter baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return images.length;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @SuppressLint("ViewHolder")
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                convertView = layoutInflater.inflate(R.layout.new_up_row, null);
                TextView textView = (TextView) convertView.findViewById(R.id.up_name);
                ImageView imageView = (ImageView) convertView.findViewById(R.id.up_image);
                textView.setText(names[position]);
                textView.setHeight(60);
                textView.setWidth(120);
                imageView.setImageResource(images[position]);
                imageView.setMaxHeight(120);
                imageView.setMaxWidth(120);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                return convertView;
            }
        };
        gridView.setAdapter(baseAdapter);
        //下方的动态列表
        ListView listView = (ListView) root.findViewById(R.id.news_content_list);
        List<Map<String, Object>> content_news = new ArrayList<Map<String, Object>>();
        for(int i=0;i<images.length;i++){
            Map<String, Object> te = new HashMap<String, Object>();
            te.put("image", images[i]);
            te.put("介绍", names[i]);
            content_news.add(te);
        }
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), content_news, R.layout.news_row, new String[]{"image", "介绍"},
                new int[]{R.id.news_image, R.id.news_des});

        //获取话题布局
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        @SuppressLint("InflateParams") View convertView = layoutInflater.inflate(R.layout.topic_news, null);
        View view = (View) convertView.findViewById(R.id.news_topic);
        ((ViewGroup)view.getParent()).removeView(view);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 450);//180+60=240:一列的宽度
        view.setLayoutParams(params1);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);//180+60=240:一列的宽度
        listView.setLayoutParams(params2);
        listView.setAdapter(adapter);
        listView.addHeaderView(view);
        return root;
    }
}