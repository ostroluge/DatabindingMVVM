package com.ostro.databindingmvvm.base.mvvm.list;

import android.app.Activity;
import android.content.res.Resources;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.ostro.databindingmvvm.BR;
import com.ostro.databindingmvvm.base.BaseActivity;
import com.ostro.databindingmvvm.base.Loader;
import com.ostro.databindingmvvm.base.mvvm.BaseViewModel;
import com.ostro.databindingmvvm.base.recycler.adapter.BindingRecyclerAdapter;
import com.ostro.databindingmvvm.base.recycler.holder.OnItemClickListener;
import com.ostro.databindingmvvm.util.NetworkUtils;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

public abstract class BaseListViewModel<O, VS extends BaseListViewState<O>> extends BaseViewModel<VS>
        implements Loader, BaseListCallback, OnItemClickListener {

    private BaseActivity mActivity;
    private BindingRecyclerAdapter<O> mAdapter;
    private Resources mResources;
    private NetworkUtils mNetworkUtils;

    private ObservableBoolean mLoading = new ObservableBoolean();
    private List<O> mList;

    public BaseListViewModel(BaseActivity baseActivity,
                             Resources resources,
                             NetworkUtils networkUtils,
                             BindingRecyclerAdapter<O> adapter) {
        mResources = resources;
        mNetworkUtils = networkUtils;
        mAdapter = adapter;
        mActivity = baseActivity;
    }

    @Override
    protected void onViewStateRestored(@NonNull VS viewState) {
        setList(getViewState().getList());
    }

    @Override
    public ObservableBoolean isLoading() {
        return mLoading;
    }

    @Override
    public void onStart() {
        super.onStart();
        loadData();
        mAdapter.setOnItemClickListener(this);
    }

    @Bindable
    public List<O> getList() {
        return mList;
    }

    public BindingRecyclerAdapter<O> getAdapter() {
        return mAdapter;
    }

    public LinearLayoutManager getLayoutManager() {
        return getLayoutManager(mActivity);
    }

    public BaseActivity getContext() {
        return mActivity;
    }

    public Resources getResources() {
        return mResources;
    }

    private void setList(List<O> list) {
        mList = list;
        notifyPropertyChanged(BR.list);
    }

    protected void loadData() {
        mLoading.set(true);
        addSubscription(loadFullData().subscribe(this::handleListResult, this::handleError));
    }

    protected Observable<List<O>> loadFullData() {
        return loadDb()
                .observeOn(AndroidSchedulers.mainThread());
    }

    protected abstract Observable<List<O>> loadDb();

    @Bindable
    public boolean isAdapterEmpty() {
        return mAdapter == null || mAdapter.isEmpty();
    }

    @Bindable
    public int getEmptyStateVisibility() {
        List<O> objects = getViewState().getList();
        int visibility;
        if (objects != null && !objects.isEmpty()) {
            visibility = View.GONE;
        } else if (isLoading().get()) {
            visibility = View.INVISIBLE;
        } else {
            visibility = View.VISIBLE;
        }
        return visibility;
    }

    public void handleListResult(List<O> list) {
        if (list != null && !list.isEmpty()) {
            getViewState().setList(list);
            updateList(list);
        }
        if (isLoading().get()) {
            changeLoadingState(false);
        }
    }

    protected void updateList(List<O> list) {
        notifyPropertyChanged(BR.emptyStateVisibility);
        setAdapterList(list);
        notifyPropertyChanged(BR.adapterEmpty);
    }

    protected void setAdapterList(List<O> list) {
        mAdapter.setItems(list);
        mAdapter.notifyDataSetChanged();
    }

    protected void changeLoadingState(boolean loading) {
        mLoading.set(loading);
        notifyPropertyChanged(BR.emptyStateVisibility);
    }

    protected void handleError(Throwable throwable) {
        Timber.w(throwable, "handleError");
        changeLoadingState(false);
        BaseListViewState<O> viewState = getViewState();
        if (!isStarted()) {
            return;
        }
//        List<O> list = viewState.getList();
//        if (list != null && !list.isEmpty()) {
//            // there is visible data
//            handleHintError(
//                    mNetworkUtils.getErrorMessage(throwable),
//                    "Réessayer",
//                    v -> loadData());
//        } else {
//            // there is no visible data
//            handleEmptyStateError(throwable);
//        }
    }

    public abstract LinearLayoutManager getLayoutManager(Activity activity);
}
