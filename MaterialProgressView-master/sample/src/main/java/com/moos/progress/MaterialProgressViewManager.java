package com.moos.progress;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.moos.library.CircleProgressView;
import com.moos.library.HorizontalProgressView;

/**
 * Created by moos on 2018/3/15.
 */

public class MaterialProgressViewManager {

    private CircleProgressView mCircleProgressView;
    private HorizontalProgressView mHorizontalProgressView;

    public MaterialProgressViewManager(CircleProgressView mCircleProgressView) {
        this.mCircleProgressView = mCircleProgressView;
    }

    public MaterialProgressViewManager(HorizontalProgressView mHorizontalProgressView) {
        this.mHorizontalProgressView = mHorizontalProgressView;
    }

    public CircleProgressView setStartProgress(int startProgress){
        mCircleProgressView.setStartProgress(startProgress);
        return this.mCircleProgressView;
    }

    public CircleProgressView setEndProgress(int endProgress){
        mCircleProgressView.setEndProgress(endProgress);
        return this.mCircleProgressView;
    }

    public CircleProgressView setStartColor(int startColor){
        mCircleProgressView.setStartColor(startColor);
        return this.mCircleProgressView;
    }
}
