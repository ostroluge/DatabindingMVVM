package com.ostro.databindingmvvm.ui.users;

import android.os.Parcel;
import android.support.annotation.Nullable;

import com.ostro.databindingmvvm.base.mvvm.list.BaseListViewState;
import com.ostro.databindingmvvm.model.User;

import java.util.List;

public class UsersViewState extends BaseListViewState<User> {

    private List<User> mUsers;

    public UsersViewState() {

    }

    @Nullable
    @Override
    public List<User> getList() {
        return mUsers;
    }

    @Override
    public void updateList(List<User> list) {
        mUsers = list;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    protected UsersViewState(Parcel in) {
        super(in);
    }

    public static final Creator<UsersViewState> CREATOR = new Creator<UsersViewState>() {
        @Override
        public UsersViewState createFromParcel(Parcel source) {
            return new UsersViewState(source);
        }

        @Override
        public UsersViewState[] newArray(int size) {
            return new UsersViewState[size];
        }
    };
}
