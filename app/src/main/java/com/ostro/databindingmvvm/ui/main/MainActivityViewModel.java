package com.ostro.databindingmvvm.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.ostro.databindingmvvm.base.mvvm.BaseViewModel;
import com.ostro.databindingmvvm.ui.user.UserFormActivity;

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

    public void userFormClick(View view) {
        Timber.d("userFormClick");
        Intent intent = new Intent(mActivity, UserFormActivity.class);
        mActivity.startActivity(intent);
    }
}
