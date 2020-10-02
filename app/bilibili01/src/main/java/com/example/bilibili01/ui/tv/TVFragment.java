package com.example.bilibili01.ui.tv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.bilibili01.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TVFragment extends Fragment {

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
        final View root = inflater.inflate(R.layout.fragment_tv, container, false);
        final Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);
        //       页面跳转设置
        ImageView imageView =(ImageView) root.findViewById(R.id.fragment1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar.setVisibility(View.VISIBLE);
                Navigation.findNavController(root).navigate(R.id.action_nav_tv_to_nav_home);
            }
        });
        ImageView imageView1 =(ImageView) root.findViewById(R.id.fragment3);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.action_nav_tv_to_nav_news);
            }
        });
        ImageView imageView2 =(ImageView) root.findViewById(R.id.fragment4);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.action_nav_tv_to_nav_shopping);
            }
        });
        //下方的动态列表
        ListView listView = (ListView) root.findViewById(R.id.tv_list_view);
        List<Map<String, Object>> content_news = new ArrayList<Map<String, Object>>();
        for(int i=0;i<images.length;i++){
            Map<String, Object> te = new HashMap<String, Object>();
            te.put("image", images[i]);
            te.put("介绍", names[i]);
            content_news.add(te);
        }
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), content_news, R.layout.tv_list_row, new String[]{"image", "介绍", "image", "介绍"},
                new int[]{R.id.tv_image, R.id.tv_text, R.id.tv_image1, R.id.tv_text1});
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        //添加滑动头部
        View convertView = layoutInflater.inflate(R.layout.tv_head_row, null);
        View view = (View) convertView.findViewById(R.id.tv_header);
        //频道横向列表
        GridView gridView = (GridView) convertView.findViewById(R.id.grid_view_tv);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                310*images.length, 900);//180+60=240:一列的宽度
        gridView.setLayoutParams(params);
        gridView.setFocusable(false);
        gridView.setColumnWidth(280);
        gridView.setMinimumHeight(560);
        gridView.setNumColumns(images.length);
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setHorizontalSpacing(30);
        gridView.setVerticalSpacing(10);
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

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                convertView = layoutInflater.inflate(R.layout.tv_header_gridview, null);
                TextView textView = (TextView) convertView.findViewById(R.id.tv_name);
                ImageView imageView = (ImageView) convertView.findViewById(R.id.tv_image_header);
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
        //继续下面列表
        ((ViewGroup)view.getParent()).removeView(view);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 1800);//180+60=240:一列的宽度
        view.setLayoutParams(params1);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);//180+60=240:一列的宽度
        listView.setLayoutParams(params2);
        listView.setAdapter(adapter);
        listView.addHeaderView(view);
        return root;
    }
}
