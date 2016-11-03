package com.lq.ren.dispatch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author lqren on 16/10/19.
 */
public class IndicatorView extends View {

    private Paint mPaintA = new Paint();
    private Paint mPaintB = new Paint();
    private int height;
    private int width;
    private int curPos = 0;


    public IndicatorView(Context context) {
        super(context);
    }

    public IndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaintA.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaintB.setColor(Color.BLUE);
        mPaintA.setColor(Color.WHITE);
        mPaintA.setStrokeWidth(5f);
        for (int i = 0; i < 4; i++) {
            if (i == curPos) {
                canvas.drawCircle(width + i *20, height, 10, mPaintB);
                canvas.drawText(""+i, width + i*20 - 3, height + 5, mPaintA);
            } else {
                mPaintA.setColor(Color.GRAY);
                canvas.drawCircle(width + i * 20, height, 5, mPaintA);
            }
        }

    }

    public void setCurrentItem(int pos) {
        curPos = pos;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getWidth() / 2 - 2 * 20;
        height = 20;
    }
}
