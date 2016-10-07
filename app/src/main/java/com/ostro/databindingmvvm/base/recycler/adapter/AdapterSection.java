package com.ostro.databindingmvvm.base.recycler.adapter;

public interface AdapterSection<A> {
    A getAdapterSection();
    boolean isHeader();
    void setHeader(boolean header);
}
