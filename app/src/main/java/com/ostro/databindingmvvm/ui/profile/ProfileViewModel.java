package com.ostro.databindingmvvm.ui.profile;

import android.support.annotation.NonNull;

import com.ostro.databindingmvvm.base.mvvm.BaseViewModel;
import com.ostro.databindingmvvm.model.User;

public class ProfileViewModel extends BaseViewModel<ProfileViewState> {

    private User mUser;

    public ProfileViewModel(User user) {
        mUser = user;
    }

    @NonNull
    @Override
    public ProfileViewState createViewState() {
        return new ProfileViewState(mUser);
    }
}
