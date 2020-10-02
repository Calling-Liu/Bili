package com.example.myintent;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class Alarm extends AppCompatActivity {
    private MediaPlayer player;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.after_alarm);
        ImageView imageView = (ImageView) findViewById(R.id.alarm_view);
        player = MediaPlayer.create(this, R.raw.alarm);
        player.start();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.stop();
                finish();
            }
        });
    }
}
