package com.example.myintent;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;

public class AlphaMain extends Activity implements GestureDetector.OnGestureListener{
    ViewFlipper flipper;
    GestureDetector detector;
    Animation[] animations = new Animation[2];
    final int distance = 50;
    private int[] images = new int[]{
            R.mipmap.image01, R.mipmap.image02, R.mipmap.image03, R.mipmap.image04, R.mipmap.image05, R.mipmap.image06,
            R.mipmap.image07
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alpha_main);
        detector = new GestureDetector(AlphaMain.this,  AlphaMain.this);
        flipper = (ViewFlipper) findViewById(R.id.filpper);
        for(int i=0;i<images.length;i++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(images[i]);
            flipper.addView(imageView);
        }
        animations[0] = AnimationUtils.loadAnimation(this, R.anim.anim_alpha_in);
        animations[1] = AnimationUtils.loadAnimation(this, R.anim.anim_alpha_out);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        flipper.setInAnimation(animations[0]);
        flipper.setInAnimation(animations[1]);
        if(e1.getX() - e2.getX() > distance){
            flipper.showPrevious();
            return true;
        }else if(e2.getX() - e1.getX() > distance){
            flipper.showNext();
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }
}
