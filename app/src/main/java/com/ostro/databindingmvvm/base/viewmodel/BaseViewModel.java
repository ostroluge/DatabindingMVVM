package com.ostro.databindingmvvm.base.viewmodel;

import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

public abstract class BaseViewModel<VIEW_STATE extends BaseViewState> extends BaseObservable {

    private boolean mStarted = false;

    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    @Nullable private VIEW_STATE mViewState;
    private boolean mSavedState = false;
    private boolean mStateRestored = false;

    @NonNull
    public VIEW_STATE getViewState() {
        if (mViewState == null) {
            mViewState = createViewState();
            Timber.i("mViewState == null, creating new");
        }
        return mViewState;
    }

    public String getModelName() {
        return getClass().getName();
    }

    void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            if(mViewState == null) {
                mViewState = getViewState();
            } else {
                onViewStateRestored(mViewState);
            }
        } else {
            restoreInstanceState(savedInstanceState);
        }
    }

    public void onStart() {
        mStarted = true;
    }

    public void onStop(boolean saveState) {
        mStarted = false;
        if (!saveState) {
            unsubscribe();
        }
        detachListeners();
    }

    public boolean isStarted() {
        return mStarted;
    }

    public void unsubscribe() {
        if (!mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    public void unsubscribe(Subscription s) {
        if (s != null) {
            s.unsubscribe();
        }
    }

    protected void addSubscription(Subscription subscription) {
        checkCompositeSubscription();
        mCompositeSubscription.add(subscription);
    }

    private void checkCompositeSubscription() {
        if (mCompositeSubscription == null || mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription = new CompositeSubscription();
        }
    }

    protected void restoreInstanceState(@Nullable Bundle bundle) {
        if (bundle != null && !mStateRestored) {
            mStateRestored = true;
            mSavedState = false;
            mViewState = bundle.getParcelable(getModelName());
            if (mViewState != null) {
                onViewStateRestored(mViewState);
            }
        }
    }

    public void onSaveInstanceState(@NonNull Bundle bundle) {
        if (!mSavedState && mViewState != null) {
            bundle.putParcelable(getModelName(), mViewState);
            mSavedState = true;
            mStateRestored = false;
        }
    }

    protected void onViewStateRestored(@NonNull VIEW_STATE viewState) {

    }

    @NonNull
    public abstract VIEW_STATE createViewState();

    private List<ListenerReference> mListenerReferences = new ArrayList<>();

    protected void addListenerReference(ListenerReference listenerReference) {
        mListenerReferences.add(listenerReference);
    }

    protected void removeListenerReference(ListenerReference listenerReference) {
        mListenerReferences.remove(listenerReference);
    }

    private void detachListeners() {
        for (ListenerReference listenerReference : mListenerReferences) {
            listenerReference.clear();
        }
        mListenerReferences.clear();
    }

    public static class ListenerReference<K> {

        private WeakReference<K> mViewRef;

        public ListenerReference(K k) {
            mViewRef = new WeakReference<>(k);
        }

        public void clear() {
            if (mViewRef != null) {
                mViewRef.clear();
                mViewRef = null;
            }
        }

        public boolean isAlive() {
            return mViewRef != null && mViewRef.get() != null;
        }

        public K get() {
            return mViewRef.get();
        }
    }
}
