package com.example.bilibili01.ui.shopping;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.bilibili01.R;

public class ShoppingFragment extends Fragment {
    private int[] images = new int[]{
            R.mipmap.image01, R.mipmap.image02, R.mipmap.image03, R.mipmap.image04, R.mipmap.image05,
            R.mipmap.image06, R.mipmap.image07
    };
    private String[] names = new String[]{
            "樱花葬，八重樱何时等到卡莲", "在那个樱花飞舞的斜坡，我遇见了我的人生", "如果喜欢有颜色的话，那一定是白色",
            "我寄生命于众生，却无人跟随", "无论何时，她总是如此", "世界扭曲而不合理，我却擿埴索途",
            "夏日的冷饮来一杯吗"
    };
    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_shopping, container, false);
        final Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);
        //       页面跳转设置
        ImageView imageView =(ImageView) root.findViewById(R.id.fragment1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar.setVisibility(View.VISIBLE);
                Navigation.findNavController(root).navigate(R.id.action_nav_shopping_to_nav_home);
            }
        });
        ImageView imageView1 =(ImageView) root.findViewById(R.id.fragment2);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.action_nav_shopping_to_nav_tv);
            }
        });
        ImageView imageView2 =(ImageView) root.findViewById(R.id.fragment3);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.action_nav_shopping_to_nav_news);
            }
        });
        //手办列表
        HeaderGridView gridView = (HeaderGridView) root.findViewById(R.id.shopping_grid);
        gridView.setColumnWidth(300);
        gridView.setHorizontalSpacing(10);
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
                convertView = layoutInflater.inflate(R.layout.shopping_grid_row, null);
                ImageView imageView = (ImageView) convertView.findViewById(R.id.shopping_card_image);
                TextView textView = (TextView) convertView.findViewById(R.id.shopping_card_text);
                imageView.setImageResource(images[position]);
                textView.setText(names[position]);
                return convertView;
            }
        };
        //获取头部布局
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View convertView = layoutInflater.inflate(R.layout.shopping_head_row, null);
        View view = (View) convertView.findViewById(R.id.news_topic_shopping);
        //        动态切换广告设置
        ViewFlipper viewFlipper = (ViewFlipper) convertView.findViewById(R.id.viewFlipper_shopping);
        for(int i=0;i<images.length;i++){
            ImageView imageViewt = new ImageView(getActivity());
            imageViewt.setImageResource(images[i]);
            imageViewt.setScaleType(ImageView.ScaleType.CENTER_CROP);
            viewFlipper.addView(imageViewt);
        }
        Animation[] animations = new Animation[2];
        animations[0] = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in);
        animations[1] = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out);
        viewFlipper.setInAnimation(animations[0]);
        viewFlipper.setOutAnimation(animations[1]);
        viewFlipper.startFlipping();
        //给view解绑父组件，否则报错
        ((ViewGroup)view.getParent()).removeView(view);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 1500);
        view.setLayoutParams(params1);
        gridView.addHeaderView(view);
        gridView.setAdapter(baseAdapter);
        return root;
    }
}
