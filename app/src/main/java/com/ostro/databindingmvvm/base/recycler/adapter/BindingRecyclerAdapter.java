package com.ostro.databindingmvvm.base.recycler.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ostro.databindingmvvm.base.recycler.holder.BindingClickableHolder;
import com.ostro.databindingmvvm.base.recycler.holder.BindingHolder;
import com.ostro.databindingmvvm.base.recycler.holder.OnItemClickListener;
import com.ostro.databindingmvvm.base.recycler.holder.OnItemLongClickListener;

import java.util.List;

public abstract class BindingRecyclerAdapter<O> extends RecyclerView.Adapter<BindingHolder> {

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    @Nullable protected List<O> mItems;

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    @Nullable
    public O getItem(int position) {
        try {
            return mItems == null ? null : mItems.get(position);
        } catch (Exception e) {
            return null;
        }
    }

    public List<O> getItems() {
        return mItems;
    }

    public void setItems(@NonNull List<O> list) {
        mItems = list;
    }

    public void notifyObjectChange(O o) {
        int index = getItemIndex(o);
        if (index >= 0) {
            notifyItemChanged(index);
        }
    }

    public void notifyListChange() {

    }

    public int getItemIndex(O o) {
        if (mItems == null) {
            return -1;
        }
        return mItems.indexOf(o);
    }

    public void setOnItemClickListener(OnItemClickListener onClickListener) {
        mOnItemClickListener = onClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onLongClickListener) {
        mOnItemLongClickListener = onLongClickListener;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BindingHolder vh = createBindingHolder(createBinding(parent, viewType), viewType);
        if (vh instanceof BindingClickableHolder) {
            ((BindingClickableHolder) vh).setOnItemClickListener(mOnItemClickListener);
            ((BindingClickableHolder) vh).setOnItemLongClickListener(mOnItemLongClickListener);
        }
        return vh;
    }

    protected ViewDataBinding createBinding(ViewGroup parent, int viewType) {
        return  DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                viewType,
                parent,
                false
        );
    }

    protected BindingHolder createBindingHolder(ViewDataBinding binding, int viewType) {
        return new BindingClickableHolder<>(binding);
    }
}