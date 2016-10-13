package com.ostro.databindingmvvm.ui.main;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;

import com.ostro.databindingmvvm.R;
import com.ostro.databindingmvvm.base.mvvm.BaseMvvmActivity;
import com.ostro.databindingmvvm.base.mvvm.BaseViewModel;
import com.ostro.databindingmvvm.databinding.ActivityMainBinding;

public class MainActivity extends BaseMvvmActivity<ActivityMainBinding> {

    private MainActivityViewModel mMainActivityViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState, ActivityMainBinding binding) {
        binding.setMainActivityVm(mMainActivityViewModel);
    }

    @Nullable
    @Override
    protected BaseViewModel[] getViewModels() {
        return new BaseViewModel[]{mMainActivityViewModel};
    }

    @Override
    public void injectDependencies() {
        mMainActivityViewModel = new MainActivityViewModel(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        disableHomeButton();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setTitle("Main Activity");
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void disableHomeButton() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            return;
        }
        actionBar.setHomeButtonEnabled(false);      // Disable the button
        actionBar.setDisplayHomeAsUpEnabled(false); // Remove the left caret
        actionBar.setDisplayShowHomeEnabled(false); // Remove the icon
    }
}
