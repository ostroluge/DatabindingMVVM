package com.ostro.databindingmvvm.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.ostro.databindingmvvm.base.mvvm.BaseViewModel;
import com.ostro.databindingmvvm.ui.users.UsersActivity;
import com.ostro.databindingmvvm.ui.sign_up.SignUpActivity;

import timber.log.Timber;

public class MainActivityViewModel extends BaseViewModel<MainActivityViewState> {

    private Activity mActivity;

    public MainActivityViewModel(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @NonNull
    @Override
    public MainActivityViewState createViewState() {
        return new MainActivityViewState();
    }

    public void signUp(View view) {
        Timber.d("signUp");
        Intent intent = new Intent(mActivity, SignUpActivity.class);
        mActivity.startActivity(intent);
    }

    public void launchList(View view) {
        Timber.d("launchList");
        Intent intent = new Intent(mActivity, UsersActivity.class);
        mActivity.startActivity(intent);
    }
}
