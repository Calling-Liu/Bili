package com.example.myactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class HeadActivity extends Activity {
    public int[] imageId = new int[]{
            R.mipmap.image01, R.mipmap.image02, R.mipmap.image03, R.mipmap.image04, R.mipmap.image05,
            R.mipmap.image06, R.mipmap.image07
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_select);
        GridView gridView = (GridView) findViewById(R.id.gridView1);
        BaseAdapter baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return imageId.length;
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
                ImageView imageView;
                if(convertView == null){
                    imageView = new ImageView(HeadActivity.this);
                    imageView.setAdjustViewBounds(true);
                    imageView.setMaxWidth(158);
                    imageView.setMaxHeight(150);
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    imageView.setPadding(5, 5, 5, 5);
                }else{
                    imageView = (ImageView)convertView;
                }
                imageView.setImageResource(imageId[position]);
                return imageView;
            }
        };
        gridView.setAdapter(baseAdapter);
//        设置监听
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                bundle.putInt("imageId", imageId[position]);//头像数据
                intent.putExtras(bundle);
                setResult(0x11, intent);
                finish();
            }
        });
    }
}
