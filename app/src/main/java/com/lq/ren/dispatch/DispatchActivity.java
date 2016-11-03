package com.lq.ren.dispatch;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author lqren on 16/10/31.
 */
public class DispatchActivity extends FragmentActivity {

    private ActionView mActionView;
    private UpView mUpView;
    private DownView mDownView;
    private ViewPager mViewPager;
    private IndicatorView mIndicatorView;
    private boolean mIsScroll = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActionView = new ActionView(this);
        setContentView(mActionView);

        mActionView.setDispatchListener(new DispatchActionListener() {
            @Override
            public void setDispatchListener(DispatchStatus status, boolean isShow) {
                handleActionEvent(status, isShow);
            }
        });

        mUpView = (UpView) findViewById(R.id.dispatch_upview);
        mDownView = (DownView) findViewById(R.id.dispatch_downview);

        mIndicatorView = (IndicatorView) findViewById(R.id.dispatch_indicatorView);
        mViewPager = (ViewPager) findViewById(R.id.dispatch_viewpager);
        DispatchAdapter adapter = new DispatchAdapter(this);
        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mIndicatorView.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("HEHE", "activity onTouchEvent");
        if (mIsScroll) {
            mViewPager.onTouchEvent(event);
        }
        return true;

    }

    private void handleActionEvent(DispatchStatus status, boolean isShow) {
        switch (status) {
            case ViewPager:
                mIsScroll = isShow;
                break;
            case Up:
                if (mDownView.getVisibility() == View.VISIBLE) {
                    mDownView.setVisibility(View.GONE);
                } else if (mUpView.getVisibility() != View.VISIBLE) {
                    mUpView.setVisibility(View.VISIBLE);
                }
                break;
            case Down:
                if (mUpView.getVisibility() == View.VISIBLE) {
                    mUpView.setVisibility(View.GONE);
                } else if (mDownView.getVisibility() != View.VISIBLE) {
                    mDownView.setVisibility(View.VISIBLE);
                }
                break;
            case Close:
                finish();
                break;
        }
    }

    private class DispatchAdapter extends PagerAdapter {

        private Context context;

        DispatchAdapter(Context context) {
            this.context = context;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ItemView view = new ItemView(context);
            view.updateView(position);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
