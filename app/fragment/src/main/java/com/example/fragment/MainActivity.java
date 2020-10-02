package com.example.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;



public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment f =null;
                switch (v.getId()){
                    case R.id.image1:
                        f = new WeChatFragment();
                        break;
                    case R.id.image2:
                        f = new WeChatFragment1();
                        break;
                    case R.id.image3:
                        f = new WeChatFragment2();
                        break;
                    case R.id.image4:
                        f= new WeChatFragment3();
                        break;
                    default:
                        break;
                }
                ft.replace(R.id.fragment, f);
                ft.commit();
            }
        };
        ImageView imageView1 = (ImageView) findViewById(R.id.image1);
        imageView1.setOnClickListener(listener);
        ImageView imageView2 = (ImageView) findViewById(R.id.image2);
        imageView2.setOnClickListener(listener);
        ImageView imageView3 = (ImageView) findViewById(R.id.image3);
        imageView3.setOnClickListener(listener);
        ImageView imageView4 = (ImageView) findViewById(R.id.image4);
        imageView4.setOnClickListener(listener);
    }
}

