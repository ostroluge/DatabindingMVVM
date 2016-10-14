package com.ostro.databindingmvvm.ui.sign_up;

import android.databinding.Bindable;
import android.os.Parcel;

import com.ostro.databindingmvvm.BR;
import com.ostro.databindingmvvm.base.mvvm.BaseViewState;
import com.ostro.databindingmvvm.model.User;

public class SignUpViewState extends BaseViewState {

    private User mUser;

    public SignUpViewState() {

    }

    public SignUpViewState(User mUser) {
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

    protected SignUpViewState(Parcel in) {
        this.mUser = in.readParcelable(User.class.getClassLoader());
    }

    public static final Creator<SignUpViewState> CREATOR = new Creator<SignUpViewState>() {
        @Override
        public SignUpViewState createFromParcel(Parcel source) {
            return new SignUpViewState(source);
        }

        @Override
        public SignUpViewState[] newArray(int size) {
            return new SignUpViewState[size];
        }
    };
}
