package com.ostro.databindingmvvm.ui.user;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.ostro.databindingmvvm.R;
import com.ostro.databindingmvvm.base.mvvm.BaseViewModel;
import com.ostro.databindingmvvm.model.User;

import io.realm.Realm;
import timber.log.Timber;

public class UserFormViewModel extends BaseViewModel<UserFormViewState> {

    private Activity mActivity;

    public UserFormViewModel(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @NonNull
    @Override
    public UserFormViewState createViewState() {
        return new UserFormViewState(new User());
    }

    public void signUpUser() {
        User user = getViewState().getUser();
        if (user != null) {
            Timber.d(user.toString());
            if (allFilledUp(user)) {
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                realm.copyToRealm(user);
                realm.commitTransaction();
                Toast.makeText(mActivity, mActivity.getString(R.string.form_signed_up_successfully), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mActivity, mActivity.getString(R.string.form_fill_in_all_fields), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean allFilledUp(User user) {
        return !TextUtils.isEmpty(user.getUsername()) &&
                !TextUtils.isEmpty(user.getFirstName()) &&
                !TextUtils.isEmpty(user.getLastName()) &&
                !TextUtils.isEmpty(user.getEmail()) &&
                !TextUtils.isEmpty(user.getPassword());
    }
}
