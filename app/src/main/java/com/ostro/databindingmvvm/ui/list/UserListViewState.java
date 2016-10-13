package com.ostro.databindingmvvm.ui.list;

import android.os.Parcel;

import com.ostro.databindingmvvm.base.mvvm.BaseViewState;

public class UserListViewState extends BaseViewState {

    public UserListViewState() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    protected UserListViewState(Parcel in) {
    }

    public static final Creator<UserListViewState> CREATOR = new Creator<UserListViewState>() {
        @Override
        public UserListViewState createFromParcel(Parcel source) {
            return new UserListViewState(source);
        }

        @Override
        public UserListViewState[] newArray(int size) {
            return new UserListViewState[size];
        }
    };
}
