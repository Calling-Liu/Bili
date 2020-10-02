package com.example.myintent;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;

import android.app.Notification;
import android.app.NotificationChannel;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setIcon(R.drawable.ic_launcher_background);
                alertDialog.setTitle("更换头像");
                alertDialog.setMessage("确定要更换自己的头像？");
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this, "取消更换", Toast.LENGTH_SHORT).show();
                        final String id = "channel_1";
                        NotificationChannel mChannel = new NotificationChannel(id, "渠道", NotificationManager.IMPORTANCE_LOW);
                        final NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.createNotificationChannel(mChannel);//创建信道
                        Notification.Builder notification = new Notification.Builder(MainActivity.this);
                        notification.setChannelId(id);//设置id
                        notification.setAutoCancel(true);
                        notification.setSmallIcon(R.drawable.ic_launcher_background);
                        notification.setContentTitle("你中奖了！");
                        notification.setContentText("查看详情");
                        notification.setDefaults(Notification.DEFAULT_ALL);
                        notification.setWhen(System.currentTimeMillis());
                        Intent intent = new Intent(MainActivity.this, ContactsActivity.class);
                        PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
                        notification.setContentIntent(pi);
                        notificationManager.notify(0x123, notification.build());
                    }
                });
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, ContactsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("image", R.mipmap.image05);
//                intent.setAction(intent.ACTION_VIEW);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                alertDialog.show();//2233
            }
        });
    }
}
