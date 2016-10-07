package com.ostro.databindingmvvm.base.viewmodel;

import android.os.Bundle;
import android.support.annotation.Nullable;

public class MvvmLifeCycleDelegate {

    @Nullable private BaseViewModel[] mViewModels;

    @Nullable
    public BaseViewModel[] getViewModels() {
        return mViewModels;
    }

    public void onCreate(@Nullable BaseViewModel[] viewModels, @Nullable Bundle savedInstanceState) {
        mViewModels = viewModels;
        if(mViewModels != null) {
            for(BaseViewModel model : mViewModels) {
                model.onCreate(savedInstanceState);
            }
        }
    }

    public void onStart(Object object) {
        if(mViewModels != null) {
            for(BaseViewModel model : mViewModels) {
                model.onStart();
            }
        }
    }

    public void onStop() {
        if(mViewModels != null) {
            for(BaseViewModel model : mViewModels) {
                model.onStop(false);
            }
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        if(mViewModels != null) {
            for(BaseViewModel model : mViewModels) {
                model.onSaveInstanceState(outState);
            }
        }
    }
}
