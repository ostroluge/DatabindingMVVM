package com.ostro.databindingmvvm.base.viewmodel.list;

import android.databinding.ObservableField;
import android.support.v7.widget.LinearLayoutManager;

import com.ostro.databindingmvvm.base.recycler.adapter.BindingRecyclerAdapter;

import java.util.List;

public interface ListProvider<O> {

    BindingRecyclerAdapter<O> getAdapter();

    LinearLayoutManager getLayoutManager();

    ObservableField<List<O>> getList();
}
