package com.ostro.databindingmvvm.ui.user;

import android.databinding.Bindable;
import android.os.Parcel;

import com.ostro.databindingmvvm.BR;
import com.ostro.databindingmvvm.base.mvvm.BaseViewState;
import com.ostro.databindingmvvm.model.User;

public class UserFormViewState extends BaseViewState {

    private User mUser;

    public UserFormViewState() {

    }

    public UserFormViewState(User mUser) {
        this.mUser = mUser;
    }

    public void setUser(User user) {
        mUser = user;
        notifyPropertyChanged(BR.user);
    }

    @Bindable
    public User getUser() {
        return mUser;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mUser, flags);
    }

    protected UserFormViewState(Parcel in) {
        this.mUser = in.readParcelable(User.class.getClassLoader());
    }

    public static final Creator<UserFormViewState> CREATOR = new Creator<UserFormViewState>() {
        @Override
        public UserFormViewState createFromParcel(Parcel source) {
            return new UserFormViewState(source);
        }

        @Override
        public UserFormViewState[] newArray(int size) {
            return new UserFormViewState[size];
        }
    };
}
