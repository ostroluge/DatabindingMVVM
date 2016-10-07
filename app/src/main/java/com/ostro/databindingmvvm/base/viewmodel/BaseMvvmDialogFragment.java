package com.ostro.databindingmvvm.base.viewmodel;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ostro.databindingmvvm.base.BaseDialogFragment;


public abstract class BaseMvvmDialogFragment<VB extends ViewDataBinding> extends BaseDialogFragment {

    private VB mBinding;

    private MvvmLifeCycleDelegate mMvvmLifeCycleDelegate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMvvmLifeCycleDelegate = new MvvmLifeCycleDelegate();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMvvmLifeCycleDelegate.onCreate(getViewModels(), savedInstanceState);
        onViewCreated(mBinding);
    }

    public VB getViewBinding() {
        return mBinding;
    }

    @Override
    public void onStart() {
        super.onStart();
        mMvvmLifeCycleDelegate.onStart(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mMvvmLifeCycleDelegate.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMvvmLifeCycleDelegate.onSaveInstanceState(outState);
    }

    protected abstract int getLayoutId();

    @Nullable
    protected abstract BaseViewModel[] getViewModels();

    protected abstract void onViewCreated(VB binding);
}