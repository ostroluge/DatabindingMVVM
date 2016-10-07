package com.ostro.databindingmvvm.base.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class ParallaxScrollListener extends RecyclerView.OnScrollListener {

    private int mCurrentScroll = 0;
    private int mMaxHeaderTranslation;
    private int mLastScroll = 0;
    private float mParallaxFactor = 0.6f;

    public ParallaxScrollListener(int maxHeaderTranslation, float parallaxFactor) {
        mMaxHeaderTranslation = maxHeaderTranslation;
        mParallaxFactor = parallaxFactor;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        mCurrentScroll += dy;
        if (mLastScroll == mMaxHeaderTranslation && mCurrentScroll > mMaxHeaderTranslation) {
            return;
        }
        if (mCurrentScroll > mMaxHeaderTranslation) {
            mCurrentScroll = mMaxHeaderTranslation;
        } else if (mCurrentScroll <= 0) {
            mCurrentScroll = 0;
        }
        mLastScroll = mCurrentScroll;
        View view = getViewTranslated();
        if (view != null) {
            view.setTranslationY(-mCurrentScroll * mParallaxFactor);
        }
    }

    public void resetScroll() {
        mCurrentScroll = 0;
        mLastScroll = 0;
    }

    public abstract View getViewTranslated();
}
