package com.ostro.databindingmvvm.ui.user;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ostro.databindingmvvm.R;
import com.ostro.databindingmvvm.base.mvvm.BaseMvvmActivity;
import com.ostro.databindingmvvm.base.mvvm.BaseViewModel;
import com.ostro.databindingmvvm.databinding.ActivityUserFormBinding;

public class UserFormActivity extends BaseMvvmActivity<ActivityUserFormBinding> {

    private UserFormViewModel mUserFormViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_form;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState, ActivityUserFormBinding binding) {
        binding.setUserFormVm(mUserFormViewModel);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    protected BaseViewModel[] getViewModels() {
        return new BaseViewModel[]{mUserFormViewModel};
    }

    @Override
    public void injectDependencies() {
        mUserFormViewModel = new UserFormViewModel(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setTitle(getActivityTitle());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private String getActivityTitle() {
        return "Sign Up";
    }
}
