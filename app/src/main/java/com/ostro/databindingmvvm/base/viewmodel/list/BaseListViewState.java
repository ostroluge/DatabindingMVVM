package com.ostro.databindingmvvm.base.viewmodel.list;

import android.databinding.ObservableBoolean;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;


import com.ostro.databindingmvvm.BR;
import com.ostro.databindingmvvm.base.viewmodel.BaseViewState;

import java.util.List;

public abstract class BaseListViewState<O>
        extends BaseViewState
        implements Parcelable {

    private ObservableBoolean mRecyclerVisible = new ObservableBoolean();

    @Nullable
    public abstract List<O> getList();

    public abstract void updateList(List<O> list);

    public void setList(List<O> list) {
        updateList(list);
        mRecyclerVisible.set(list != null && !list.isEmpty());
        notifyPropertyChanged(BR.list);
    }

    public ObservableBoolean isRecyclerVisible() {
        return mRecyclerVisible;
    }

    public BaseListViewState() {}

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mRecyclerVisible, flags);
    }

    protected BaseListViewState(Parcel in) {
        this.mRecyclerVisible = in.readParcelable(ObservableBoolean.class.getClassLoader());
    }
}