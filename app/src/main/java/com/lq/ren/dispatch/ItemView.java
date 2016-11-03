package com.lq.ren.dispatch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Author lqren on 16/10/31.
 */
public class ItemView extends View {

    Paint paint = new Paint();
    int[] colors = {Color.YELLOW, Color.BLUE, Color.CYAN, Color.BLACK};

    public ItemView(Context context) {
        super(context);
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 100, paint);
    }

    public void updateView(int index) {
        paint.setColor(colors[index]);
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

}
