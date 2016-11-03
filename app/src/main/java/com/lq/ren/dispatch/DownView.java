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
public class DownView extends View{


    public DownView(Context context) {
        super(context);
    }

    public DownView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getWidth() / 2, getHeight() / 2, paint);
    }



}
