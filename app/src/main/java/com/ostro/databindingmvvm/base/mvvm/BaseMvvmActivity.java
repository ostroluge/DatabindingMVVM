package com.ostro.databindingmvvm.base.mvvm;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ostro.databindingmvvm.base.BaseActivity;


public abstract class BaseMvvmActivity<VB extends ViewDataBinding> extends BaseActivity {

    private MvvmLifeCycleDelegate mMvvmLifeCycleDelegate;
    private VB mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMvvmLifeCycleDelegate = new MvvmLifeCycleDelegate();
        mMvvmLifeCycleDelegate.onCreate(getViewModels(), savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, getLayoutId());
        onViewCreated(savedInstanceState, mBinding);
    }

    public VB getViewBinding() {
        return mBinding;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMvvmLifeCycleDelegate.onStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMvvmLifeCycleDelegate.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMvvmLifeCycleDelegate.onSaveInstanceState(outState);
    }

    protected abstract int getLayoutId();

    protected abstract void onViewCreated(Bundle savedInstanceState, VB binding);

    @Nullable
    protected abstract BaseViewModel[] getViewModels();
}
