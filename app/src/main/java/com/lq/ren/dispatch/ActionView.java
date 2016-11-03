package com.lq.ren.dispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;


/**
 * Author lqren on 16/10/31.
 */
public class ActionView extends RelativeLayout implements View.OnClickListener {

    private static final int LONG_TOUCH_TIME = 1000;
    private static final int ORIENTATION_LEFTRIGHT = 1;
    private static final int ORIENTATION_UPDOWN = 2;
    private float mDownX;
    private float mDownY;
    private long mLastTouchTime;
    private int mOrientationId = 0;
    private float mTouchSlop = 240;
    private View mMask;
    private DispatchActionListener mDispatchListener;

    public ActionView(Context context) {
        super(context);
        init();
    }

    public ActionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ActionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.custom_dispatch_action, this, true);
        mMask = findViewById(R.id.mask);
        findViewById(R.id.close).setOnClickListener(this);
        mMask.setOnClickListener(this);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mMask.getVisibility() != VISIBLE) {
            final float currentX = ev.getX();
            final float currentY = ev.getY();
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mDownX = ev.getX();
                    mDownY = ev.getY();
                    if (findViewById(R.id.dispatch_upview).getVisibility() != VISIBLE
                            && findViewById(R.id.dispatch_downview).getVisibility() != VISIBLE) {
                        Log.d("HEHE", "ActionDown viewpager true");
                        mDispatchListener.setDispatchListener(DispatchStatus.ViewPager, true);
                    } else {
                        Log.d("HEHE", "ActionDown viewpager false");
                        mDispatchListener.setDispatchListener(DispatchStatus.ViewPager, false);
                    }
                    mLastTouchTime = System.currentTimeMillis();
                    break;
                case MotionEvent.ACTION_UP:
                    if (mOrientationId == ORIENTATION_LEFTRIGHT) {
                        Log.i("HEHE", "ACTION_UP viewpager true");
                        mDispatchListener.setDispatchListener(DispatchStatus.ViewPager, true);
                    } else if (mOrientationId == ORIENTATION_UPDOWN) {
                        if (currentY - mDownY <= mTouchSlop) {
                            Log.i("HEHE", "ACTION_UP Up true");
                            mDispatchListener.setDispatchListener(DispatchStatus.Up, true);
                        } else if (currentY - mDownY > mTouchSlop) {
                            Log.i("HEHE", "ACTION_UP Down true");
                            mDispatchListener.setDispatchListener(DispatchStatus.Down, true);
                        }
                    } else if (System.currentTimeMillis() - mLastTouchTime > LONG_TOUCH_TIME) {
                        Log.i("HEHE", "ACTION_UP long true");
                        mMask.setVisibility(View.VISIBLE);
                    }
                    mOrientationId = 0;
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (mOrientationId == 0) {
                        if (Math.abs(currentX - mDownX) > Math.abs(currentY - mDownY)
                                && Math.abs(currentX - mDownX) > mTouchSlop) {
                            Log.e("HEHE", "ACTION_MOVE mOrientationId == left right" );
                            mOrientationId = ORIENTATION_LEFTRIGHT;
                        } else if (Math.abs(currentX - mDownX) < Math.abs(currentY - mDownY)
                                && Math.abs(currentY - mDownY) > mTouchSlop) {
                            Log.e("HEHE", "ACTION_MOVE mOrientationId == up down");
                            mOrientationId = ORIENTATION_UPDOWN;
                        }
                    }
                    break;
                case MotionEvent.ACTION_CANCEL:
                    mOrientationId = 0;
                    break;
            }
        }
        return super.dispatchTouchEvent(ev);

    }


    @Override
    public void onClick(View view) {
        Log.d("HEHE", "action onClick");
        switch (view.getId()) {
            case R.id.mask:
                mMask.setVisibility(View.GONE);
                break;
            case R.id.close:
                mMask.setVisibility(View.GONE);
                mDispatchListener.setDispatchListener(DispatchStatus.Close, false);
                break;
        }
    }

    public void setDispatchListener(DispatchActionListener listener) {
        mDispatchListener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("HEHE", "action onTouchEvent");
        return true;
    }
}
