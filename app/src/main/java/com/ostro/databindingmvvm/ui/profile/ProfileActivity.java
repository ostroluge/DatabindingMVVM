package com.ostro.databindingmvvm.ui.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ostro.databindingmvvm.R;
import com.ostro.databindingmvvm.base.mvvm.BaseMvvmActivity;
import com.ostro.databindingmvvm.base.mvvm.BaseViewModel;
import com.ostro.databindingmvvm.databinding.ActivityProfileBinding;
import com.ostro.databindingmvvm.model.User;

public class ProfileActivity extends BaseMvvmActivity<ActivityProfileBinding> {

    private ProfileViewModel mProfileViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_profile;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState, ActivityProfileBinding binding) {
        binding.setProfileVm(mProfileViewModel);
    }

    @Nullable
    @Override
    protected BaseViewModel[] getViewModels() {
        return new BaseViewModel[]{mProfileViewModel};
    }

    @Override
    public void injectDependencies() {
        User user = getUserExtra();
        if (user != null) {
            mProfileViewModel = new ProfileViewModel(user);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        setTitle("User Profile");
    }

    @Nullable
    private User getUserExtra() {
        return getIntent().getExtras().getParcelable("USER");
    }
}
