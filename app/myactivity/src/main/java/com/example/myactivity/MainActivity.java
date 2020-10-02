package com.example.myactivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_image);
        //向调用方发送消息
//        Button button_login = (Button) findViewById(R.id.button_login);
//        button_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String user = ((EditText) findViewById(R.id.user)).getText().toString();//账号
//                String password = ((EditText) findViewById(R.id.password)).getText().toString();//密码
//                if(!"".equals(user) && !"".equals(password)){
//                    Intent intent = new Intent(MainActivity.this, Logined.class);//启动另一活动
//                    Bundle bundle = new Bundle();
//                    bundle.putCharSequence("user", user);
//                    bundle.putCharSequence("password", password);
//                    intent.putExtras(bundle);
//                    startActivity(intent);
//                }else{
//                    Toast.makeText(MainActivity.this, "账号或者密码不可为空", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
        //回传信息
        Button button = (Button) findViewById(R.id.button_image);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HeadActivity.class);
                startActivityForResult(intent, 0x11);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0x11 && resultCode == 0x11){
            Bundle bundle = data.getExtras();
            int imageId = bundle.getInt("imageId");
            ImageView iv = (ImageView) findViewById(R.id.touxiang);
            iv.setImageResource(imageId);
            Toast.makeText(MainActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
        }
    }
}