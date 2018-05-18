package com.example.buitiendat.myapplication;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;

/**
 * Created by bui.tien.dat on 4/3/18.
 */

public class ResizeAnimation extends Animation {

    private int startHeight;
    private int deltaHeight;
    private int startWidth;
    private int deltaWidth;
    private View outerView;
    private boolean isHeight = true;

    public ResizeAnimation(View outerView) {
        this.outerView = outerView;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        if (isHeight) {
            outerView.getLayoutParams().height = (int) (startHeight + deltaHeight * interpolatedTime);
            outerView.getLayoutParams().width = (int) (startWidth + deltaWidth * interpolatedTime);
            outerView.requestLayout();
        } else {
            outerView.getLayoutParams().height = (int) (startHeight + deltaHeight * interpolatedTime);
            outerView.requestLayout();
        }

    }

    public void setStartParams(int startHeight, int endHeight, int startWidth, int endWidth) {

        this.startHeight = startHeight;
        deltaHeight = endHeight - startHeight;
        this.startWidth = startWidth;
        deltaWidth = endWidth - startWidth;

    }

    public void setStartParams(int startHeight, int endHeight, boolean isHeight) {
        this.startHeight = startHeight;
        deltaHeight = endHeight - startHeight;
        this.isHeight = isHeight;
    }

    @Override
    public void setDuration(long durationMillis) {
        super.setDuration(durationMillis);
    }

    @Override
    protected void ensureInterpolator() {
        super.ensureInterpolator();
    }

    @Override
    public void setInterpolator(Interpolator i) {
        super.setInterpolator(i);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}
