package com.ostro.databindingmvvm.ui.users;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.ostro.databindingmvvm.base.BaseActivity;
import com.ostro.databindingmvvm.base.mvvm.list.BaseListViewModel;
import com.ostro.databindingmvvm.base.recycler.adapter.BindingRecyclerAdapter;
import com.ostro.databindingmvvm.model.User;
import com.ostro.databindingmvvm.ui.profile.ProfileActivity;
import com.ostro.databindingmvvm.util.NetworkUtils;

import java.util.List;

import io.realm.Realm;
import rx.Observable;
import timber.log.Timber;

public class UsersViewModel extends BaseListViewModel<User, UsersViewState> {

    private Activity mActivity;

    public UsersViewModel(BaseActivity baseActivity,
                          Resources resources,
                          NetworkUtils networkUtils,
                          BindingRecyclerAdapter<User> adapter) {

        super(baseActivity, resources, networkUtils, adapter);
        mActivity = baseActivity;
    }

    @NonNull
    @Override
    public UsersViewState createViewState() {
        return new UsersViewState();
    }

    @Override
    public LinearLayoutManager getLayoutManager(Activity activity) {
        return new LinearLayoutManager(activity);
    }

    @Override
    protected Observable<List<User>> loadDb() {
        final Realm realm = Realm.getDefaultInstance();
        return realm.where(User.class)
                .findAllAsync()
                .asObservable()
                .map(users -> users)
                .map(realm::copyFromRealm);
    }

    @Override
    public void onItemClick(View view, int position) {
        User user = getAdapter().getItem(position);
        if (user == null) {
            return;
        }
        Intent intent = new Intent(mActivity, ProfileActivity.class);
        intent.putExtra("USER", user);
        mActivity.startActivity(intent);
    }

    @Override
    public void handleEmptyStateError(Throwable throwable) {

    }

    @Override
    public void handleHintError(String reason, String action, View.OnClickListener onClickListener) {

    }
}
