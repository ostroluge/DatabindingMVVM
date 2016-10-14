package com.ostro.databindingmvvm.ui.users;

import android.databinding.ViewDataBinding;

import com.ostro.databindingmvvm.R;
import com.ostro.databindingmvvm.base.recycler.adapter.BindingRecyclerAdapter;
import com.ostro.databindingmvvm.base.recycler.holder.BindingClickableHolder;
import com.ostro.databindingmvvm.base.recycler.holder.BindingHolder;
import com.ostro.databindingmvvm.databinding.ListItemUserBinding;
import com.ostro.databindingmvvm.model.User;

public class UserAdapter extends BindingRecyclerAdapter<User> {

    private UserItemViewModel mUserItemViewModel;

    public UserAdapter(UserItemViewModel mUserItemViewModel) {
        this.mUserItemViewModel = mUserItemViewModel;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.list_item_user;
    }

    @Override
    protected BindingHolder createBindingHolder(ViewDataBinding binding, int viewType) {
        return new UserViewHolder((ListItemUserBinding) binding, mUserItemViewModel);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        ListItemUserBinding binding = (ListItemUserBinding) holder.getBinding();
        User user = getItem(position);
        mUserItemViewModel.setUser(user);
        binding.setUserVm(mUserItemViewModel);
        binding.executePendingBindings();
    }

    public static class UserViewHolder extends BindingClickableHolder<ListItemUserBinding> {

        private UserItemViewModel mUserItemViewModel;

        public UserViewHolder(ListItemUserBinding binding, UserItemViewModel userItemViewModel) {
            super(binding);
            mUserItemViewModel = userItemViewModel;
        }
    }
}
