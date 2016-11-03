package com.lq.ren.dispatch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author lqren on 16/10/31.
 */
public class UpView extends View {

    public UpView(Context context) {
        super(context);
    }

    public UpView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UpView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawColor(Color.RED);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        Log.d("HEHE", "up onTouchEvent");
//        return false;
//    }

}
