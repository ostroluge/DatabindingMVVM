package com.ostro.databindingmvvm.base.mvvm.list;

import android.view.View;

public interface BaseListCallback {

    void handleEmptyStateError(Throwable throwable);

    void handleHintError(String reason, String action, View.OnClickListener onClickListener);
}
