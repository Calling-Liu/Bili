package com.example.qqchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends Activity {
    private ProgressBar horizonP;//进度条
    private int m=0;//进度
    private Handler mHandler;//处理消息的内部类
    private Integer[] picture = {
            R.mipmap.image01, R.mipmap.image02, R.mipmap.image03, R.mipmap.image04, R.mipmap.image05,
            R.mipmap.image06, R.mipmap.image07
    };
    public class ImageAdapter extends BaseAdapter{
        private Context mContext;
        public ImageAdapter(Context c){
            mContext = c;
        }

        @Override
        public int getCount() {
            return picture.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if(convertView == null){
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(260, 260));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }else{
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(picture[position]);
            return imageView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ListView listView = (ListView)findViewById(R.id.listView);
        int[] imageId = new int[]{
                R.mipmap.image05, R.mipmap.image05, R.mipmap.image05, R.mipmap.image05, R.mipmap.image05, R.mipmap.image05,
                R.mipmap.image05
        };
        String[] title = new String[]{
             "刘一", "陈二", "张三", "李四", "王五", "赵六", "孙琦"
        };
        List<Map<String, Object>> listItems = new ArrayList<>();
        for(int i=0;i<imageId.length;i++){
            Map<String, Object> temp = new HashMap<>();
            temp.put("image", imageId[i]);
            temp.put("姓名", title[i]);
            listItems.add(temp);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.main, new String[]{"姓名", "image"},
                new int[]{R.id.title, R.id.image});
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, map.get("姓名").toString(), Toast.LENGTH_SHORT).show();
            }
        });
        //网格视图
//        GridView gridView = (GridView) findViewById(R.id.gridView);
//        gridView.setAdapter(new ImageAdapter(this));
        //进度条
//        horizonP = (ProgressBar)findViewById(R.id.processBar1);
//        mHandler = new Handler(){
//            @Override
//            public void handleMessage(@NonNull Message msg) {
//                super.handleMessage(msg);
//                if(msg.what == 0x111){
//                    horizonP.setProgress(m);
//                }else{
//                    Toast.makeText(MainActivity.this, "加载完成", Toast.LENGTH_SHORT).show();
//                    horizonP.setVisibility(View.GONE);
//                }
//            }
//        };
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true){
//                    m = doWork();
//                    Message mt = new Message();
//                    if(m < 100){
//                        mt.what = 0x111;
//                        mHandler.sendMessage(mt);
//                    }else{
//                        mt.what = 0x110;
//                        mHandler.sendMessage(mt);
//                        break;
//                    }
//                }
//            }
//        }).start();
    }
//    private int doWork(){
//        m += Math.random()*10;
//        try{
//            Thread.sleep(200);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//        return m;
//    }
}

//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        final EditText shuo_shuo = (EditText)findViewById(R.id.edit1);
//        final EditText xian_shi = (EditText)findViewById(R.id.edit2);
//
//        shuo_shuo.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                String content = shuo_shuo.getText().toString();
//                xian_shi.setText(content);
//            }
//        });
//    }
//}
