package com.example.bilibili01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import java.util.Random;

public class StartActivity extends Activity {
    private int[] images = new int[]{
            R.mipmap.bilibili01, R.mipmap.bilibili02, R.mipmap.bilibili03, R.mipmap.bilibili04,
            R.mipmap.bilibili05, R.mipmap.bilibili07, R.mipmap.bilibili08
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);
        ImageView imageView = (ImageView)findViewById(R.id.start_image);
        Random random = new Random();
        imageView.setImageResource(images[random.nextInt(7)]);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                StartActivity.this.finish();
            }
        }, 2000);
    }
}
