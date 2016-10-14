package com.ostro.databindingmvvm.ui.profile;

import android.databinding.Bindable;
import android.os.Parcel;

import com.ostro.databindingmvvm.BR;
import com.ostro.databindingmvvm.base.mvvm.BaseViewState;
import com.ostro.databindingmvvm.model.User;

public class ProfileViewState extends BaseViewState {

    private User mUser;

    public ProfileViewState(User user) {
        mUser = user;
    }

    public void setUserProfile(User user) {
        mUser = user;
        notifyPropertyChanged(BR.userProfile);
    }

    @Bindable
    public User getUserProfile() {
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

    protected ProfileViewState(Parcel in) {
        this.mUser = in.readParcelable(User.class.getClassLoader());
    }

    public static final Creator<ProfileViewState> CREATOR = new Creator<ProfileViewState>() {
        @Override
        public ProfileViewState createFromParcel(Parcel source) {
            return new ProfileViewState(source);
        }

        @Override
        public ProfileViewState[] newArray(int size) {
            return new ProfileViewState[size];
        }
    };
}
