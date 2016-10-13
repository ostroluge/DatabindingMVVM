package com.ostro.databindingmvvm.ui.list;

import android.support.annotation.NonNull;

import com.ostro.databindingmvvm.base.mvvm.BaseViewModel;

public class UserListViewModel extends BaseViewModel<UserListViewState> {

    @NonNull
    @Override
    public UserListViewState createViewState() {
        return new UserListViewState();
    }
}
