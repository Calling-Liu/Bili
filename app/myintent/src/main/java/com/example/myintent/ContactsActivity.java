package com.example.myintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class ContactsActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        ImageView imageView = (ImageView) findViewById(R.id.image_big);
        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        imageView.setImageResource(bundle.getInt("image"));
    }
}
