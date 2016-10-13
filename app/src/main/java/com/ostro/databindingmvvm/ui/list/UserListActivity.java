package com.ostro.databindingmvvm.ui.list;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ostro.databindingmvvm.R;
import com.ostro.databindingmvvm.base.mvvm.BaseMvvmActivity;
import com.ostro.databindingmvvm.base.mvvm.BaseViewModel;
import com.ostro.databindingmvvm.databinding.ActivityUserListBinding;

public class UserListActivity extends BaseMvvmActivity<ActivityUserListBinding> {

    private UserListViewModel mUserListViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_list;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState, ActivityUserListBinding binding) {

    }

    @Nullable
    @Override
    protected BaseViewModel[] getViewModels() {
        return new BaseViewModel[]{mUserListViewModel};
    }

    @Override
    public void injectDependencies() {
        mUserListViewModel = new UserListViewModel();
    }
}
