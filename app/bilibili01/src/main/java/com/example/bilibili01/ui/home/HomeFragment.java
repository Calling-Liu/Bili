package com.example.bilibili01.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bilibili01.MainActivity;
import com.example.bilibili01.R;
import com.example.bilibili01.adapter.RecycleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ViewFlipper viewFlipper;
    private Message message;
    //切换的图片资源
    private int[] images = new int[]{
            R.mipmap.image01, R.mipmap.image02, R.mipmap.image03, R.mipmap.image04,
            R.mipmap.image06, R.mipmap.image07
    };
    private Animation[] animations = new Animation[2];
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    //上层列表
    private String[] checked = new String[]{
            "直播", "推荐", "热门", "追番",
            "风犬", "影视", "说唱区", "小康"
    };
    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        final  View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final View v = inflater.inflate(R.layout.content_main, container, false);


//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//       页面跳转设置
        final ImageView imageView =(ImageView) root.findViewById(R.id.fragment2);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.action_nav_home_to_nav_tv);
            }
        });
        ImageView imageView1 =(ImageView) root.findViewById(R.id.fragment3);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.action_nav_home_to_nav_news);
            }
        });
        ImageView imageView2 =(ImageView) root.findViewById(R.id.fragment4);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.action_nav_home_to_nav_shopping);
            }
        });
        //        动态切换广告设置
        viewFlipper = (ViewFlipper) root.findViewById(R.id.viewFlipper);
        for(int i=0;i<images.length;i++){
            ImageView imageViewt = new ImageView(getActivity());
            imageViewt.setImageResource(images[i]);
            imageViewt.setScaleType(ImageView.ScaleType.CENTER_CROP);
            viewFlipper.addView(imageViewt);
        }
        animations[0] = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in);
        animations[1] = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out);
        viewFlipper.setInAnimation(animations[0]);
        viewFlipper.setOutAnimation(animations[1]);
        viewFlipper.startFlipping();
//        内容列表
//        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recycle_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));   //recycleView必须要布局管理器
//        String[] mDataSet = new String[100];
//        for (int i = 0;i<100;i++){
//            mDataSet[i] = "This is item "+ i;
//        }
//        recyclerView = (RecyclerView) root.findViewById(R.id.recycle_view);
//        if(recyclerView != null)
//            System.out.println(1);
//        recyclerView.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(layoutManager);
//        mAdapter = new RecycleAdapter(mDataSet);
//        recyclerView.setAdapter(mAdapter);
        ListView listView = (ListView) root.findViewById(R.id.list_content);
        String[] title = new String[]{"八重樱，永存于时间裂缝。---《崩坏三》", "加藤惠，路人女主结束了，她依旧如此。---《路人女主的养成方式》",
                "椎名真白，如果喜欢是有颜色的，那一定是樱花色。---《樱花庄的宠物女孩》", "呆毛王，虽已然逝去，人们依旧称她如青莲。---《Fate》",
                "渡边早季，在生物的进化过程中，没有能力的人被变为化鼠。---《新世界より》", "加藤惠，路人女主结束了，她依旧如此。---《路人女主的养成方式》"};
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for(int i=0;i<images.length/2;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", images[2*i]);
            map.put("介绍", title[2*i]);
            map.put("image1", images[2*i+1]);
            map.put("介绍1", title[2*i + 1]);
            listItems.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), listItems, R.layout.recycle_view_row, new String[]{"image", "介绍",
                "image1", "介绍1"},
                new int[]{R.id.image01, R.id.text01, R.id.image02, R.id.text02});
        listView.setAdapter(adapter);
//        上层滑动列表
        final GridView gridView = (GridView) root.findViewById(R.id.grid_view);
        BaseAdapter baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return checked.length;
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
                TextView textView;
                if(convertView == null){
                    textView = new TextView(getActivity());
                    //要想滑动必须指定宽度
                    textView.setWidth(240);
                    textView.setHeight(90);
                    textView.setTextColor(Color.rgb(255, 255, 255));
                }else{
                    textView = (TextView)convertView;
                }
                textView.setText(checked[position]);
                return textView;
            }
        };
        gridView.setAdapter(baseAdapter);
        //监听
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //触发事件
                //.......
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
