package com.ostro.databindingmvvm.base.recycler.holder;

import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.view.View;

public class BindingClickableHolder<VB extends ViewDataBinding> extends BindingHolder<VB> implements
        View.OnClickListener,
        View.OnLongClickListener  {

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    @Nullable
    protected View getClickableView() {
        return null;
    }

    public BindingClickableHolder(VB binding) {
        super(binding);
    }

    public void setOnItemClickListener(final OnItemClickListener onItemClickListener) {
        View view = getClickableView();
        if (view != null) {
            bindClick(view, onItemClickListener);
        } else {
            bindClick(itemView, onItemClickListener);
        }
    }

    private void bindClick(View view, final OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (mOnItemLongClickListener == null) {
            return false;
        }
        mOnItemLongClickListener.onItemLongClick(v, getAdapterPosition());
        return true;
    }

    public void setOnItemLongClickListener(final OnItemLongClickListener onItemLongClickListener) {
        View view = getClickableView();
        if (view != null) {
            bindLongClick(view, onItemLongClickListener);
        } else {
            bindLongClick(itemView, onItemLongClickListener);
        }
    }

    private void bindLongClick(View view, final OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
        view.setOnLongClickListener(this);
    }
}