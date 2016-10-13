package com.ostro.databindingmvvm.ui.main;

import android.os.Parcel;

import com.ostro.databindingmvvm.base.mvvm.BaseViewState;

public class MainActivityViewState extends BaseViewState {

    public MainActivityViewState() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    protected MainActivityViewState(Parcel in) {
    }

    public static final Creator<MainActivityViewState> CREATOR = new Creator<MainActivityViewState>() {
        @Override
        public MainActivityViewState createFromParcel(Parcel source) {
            return new MainActivityViewState(source);
        }

        @Override
        public MainActivityViewState[] newArray(int size) {
            return new MainActivityViewState[size];
        }
    };
}
