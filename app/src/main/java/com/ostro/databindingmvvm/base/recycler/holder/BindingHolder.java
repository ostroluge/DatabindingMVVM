package com.ostro.databindingmvvm.base.recycler.holder;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

public class BindingHolder<VB extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private VB mBinding;

    public BindingHolder(VB binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public VB getBinding() {
        return mBinding;
    }
}