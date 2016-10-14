package com.ostro.databindingmvvm.ui.users;

import android.content.res.Resources;

import com.ostro.databindingmvvm.model.User;

public class UserItemViewModel {

    private User mUser;
    private UserAdapter mUserAdapter;
    private Resources mResources;

    public UserItemViewModel(Resources resources) {
        mResources = resources;
    }

    public void setAdapter(UserAdapter userAdapter) {
        mUserAdapter = userAdapter;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User mUser) {
        this.mUser = mUser;
    }

    public String getEmail() {
        if (mUser == null) {
            return null;
        }
        return mUser.getEmail();
    }
}
