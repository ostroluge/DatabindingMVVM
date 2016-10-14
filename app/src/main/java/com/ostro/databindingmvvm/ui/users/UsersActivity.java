package com.ostro.databindingmvvm.ui.users;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ostro.databindingmvvm.R;
import com.ostro.databindingmvvm.base.mvvm.BaseMvvmActivity;
import com.ostro.databindingmvvm.base.mvvm.BaseViewModel;
import com.ostro.databindingmvvm.databinding.ActivityUsersBinding;
import com.ostro.databindingmvvm.util.NetworkUtils;

public class UsersActivity extends BaseMvvmActivity<ActivityUsersBinding> {

    private UsersViewModel mUserListViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_users;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState, ActivityUsersBinding binding) {
        binding.setUsersVm(mUserListViewModel);
    }

    @Nullable
    @Override
    protected BaseViewModel[] getViewModels() {
        return new BaseViewModel[]{mUserListViewModel};
    }

    @Override
    public void injectDependencies() {
        UserItemViewModel userItemViewModel = new UserItemViewModel(getResources());

        mUserListViewModel = new UsersViewModel(this,
                this.getResources(),
                NetworkUtils.get(),
                new UserAdapter(userItemViewModel));
    }

    @Override
    protected void onStart() {
        super.onStart();
        setTitle("User List");
    }
}
