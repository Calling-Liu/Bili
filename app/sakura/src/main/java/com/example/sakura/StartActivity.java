package com.example.sakura;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import androidx.annotation.Nullable;

public class StartActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                StartActivity.this.finish();
            }
        }, 3000);
    }
}
