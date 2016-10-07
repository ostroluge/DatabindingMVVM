package com.ostro.databindingmvvm.base.rx;


import java.util.concurrent.TimeUnit;

import rx.subjects.ReplaySubject;


public class RxReplaySubject<T> {

    private ReplaySubject<T> mReplaySubject;
    private boolean mHasTimeValidity = false;
    private long mExpireDate;

    private RxReplaySubject() {
        this(0, null);
    }

    private RxReplaySubject(long duration, TimeUnit timeUnit) {
        if(timeUnit == null) {
            mHasTimeValidity = false;
        } else {
            mHasTimeValidity = true;
            mExpireDate = timeUnit.toMillis(duration) + System.currentTimeMillis();
        }
        mReplaySubject = ReplaySubject.create();
    }

    public static <T> RxReplaySubject<T> create() {
        return new RxReplaySubject<>();
    }

    public static <T> RxReplaySubject<T> createWithTime(long duration, TimeUnit timeUnit) {
        return new RxReplaySubject<>(duration, timeUnit);
    }

    public boolean isValid() {
        if(mReplaySubject == null || mReplaySubject.hasThrowable()) {
            return false;
        }
        if(mHasTimeValidity) {
            return mExpireDate > System.currentTimeMillis();
        } else {
            return true;
        }
    }

    public ReplaySubject<T> subject() {
        return mReplaySubject;
    }
}
