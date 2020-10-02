package com.example.myintent;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.Calendar;

public class AlermActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_main);
        final TimePicker timePicker;
        timePicker = (TimePicker) findViewById(R.id.alarm);
        timePicker.setIs24HourView(true);
        //时区对象必须为UTC+8
        Button button = (Button) findViewById(R.id.button);
        final ImageButton imageButton = (ImageButton) findViewById(R.id.select_image);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlermActivity.this, HeadActivity.class);
                stopService(new Intent(AlermActivity.this, AlarmService.class));
                startActivityForResult(intent, 0x11);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("ShortAlarm")
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();;//日历对象
                Intent intent = new Intent(AlermActivity.this, Alarm.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(AlermActivity.this, 0, intent, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                System.out.println(timePicker.getCurrentHour());
                System.out.println(timePicker.getCurrentMinute());
                c.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                c.set(Calendar.MINUTE, timePicker.getMinute());
                c.set(Calendar.SECOND, 0);
                alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
//                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 5*60*1000, pendingIntent);
                Toast.makeText(AlermActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
            }
        });
//        Button button1 = (Button) findViewById(R.id.buttont);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startService(new Intent(AlermActivity.this, AlarmService.class));
//            }
//        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0x11 && resultCode == 0x11){
            Bundle bundle = data.getExtras();
            int imageId = bundle.getInt("imageId");
            ImageView iv = (ImageView) findViewById(R.id.back);
            iv.setImageResource(imageId);
            Toast.makeText(AlermActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
        }
    }
}
