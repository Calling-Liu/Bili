package com.example.finger;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class RabbitView extends View{
    public float x;
    public float y;
    public RabbitView(Context context){
        super(context);
        x = 210;
        y = 130;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.rabit);
        canvas.drawBitmap(bitmap, x, y, paint);
        if(!bitmap.isRecycled()){
            bitmap.recycle();
        }
    }
}
