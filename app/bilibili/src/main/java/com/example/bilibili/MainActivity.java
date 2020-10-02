package com.example.bilibili;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;

import com.example.bilibili.ui.home.HomeFragment;
import com.example.bilibili.ui.news.NewsFragment;
import com.example.bilibili.ui.shopping.ShoppingFragment;
import com.example.bilibili.ui.tv.TVFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;


import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private AppBarConfiguration mAppBarConfiguration1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        mAppBarConfiguration1 = new AppBarConfiguration.Builder(
                R.id.nav_news, R.id.nav_shopping, R.id.nav_tv
                )
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupActionBarWithNavController(this, navController);
        NavigationUI.setupWithNavController(navigationView, navController);

//        下面的选项
        ImageView imageView1 = (ImageView) findViewById(R.id.fragment1);
        ImageView imageView2 = (ImageView) findViewById(R.id.fragment2);
        ImageView imageView3 = (ImageView) findViewById(R.id.fragment3);
        ImageView imageView4 = (ImageView) findViewById(R.id.fragment4);
        View.OnClickListener l = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment f = null;
                switch (v.getId()){
                    case R.id.fragment1:
                        f = new HomeFragment();
                        break;
                    case R.id.fragment2:
                        f = new TVFragment();
                        break;
                    case R.id.fragment3:
                        f = new NewsFragment();
                        break;
                    case R.id.fragment4:
                        f = new ShoppingFragment();
                        break;
                    default:
                        break;
                }
                ft.replace(R.id.nav_host_fragment, f);
                ft.commit();
            }
        };
        imageView1.setOnClickListener(l);
        imageView2.setOnClickListener(l);
        imageView3.setOnClickListener(l);
        imageView4.setOnClickListener(l);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
