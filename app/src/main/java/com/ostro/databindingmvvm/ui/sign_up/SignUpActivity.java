package com.ostro.databindingmvvm.ui.sign_up;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ostro.databindingmvvm.R;
import com.ostro.databindingmvvm.base.mvvm.BaseMvvmActivity;
import com.ostro.databindingmvvm.base.mvvm.BaseViewModel;
import com.ostro.databindingmvvm.databinding.ActivitySignUpBinding;

public class SignUpActivity extends BaseMvvmActivity<ActivitySignUpBinding> {

    private SignUpViewModel mSignUpViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sign_up;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState, ActivitySignUpBinding binding) {
        binding.setSignUpVm(mSignUpViewModel);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    protected BaseViewModel[] getViewModels() {
        return new BaseViewModel[]{mSignUpViewModel};
    }

    @Override
    public void injectDependencies() {
        mSignUpViewModel = new SignUpViewModel(this);
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
