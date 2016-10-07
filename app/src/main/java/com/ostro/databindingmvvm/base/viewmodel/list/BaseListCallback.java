package com.ostro.databindingmvvm.base.viewmodel.list;

import android.view.View;

/**
 * Created by Nicolas Dumont
 * nicolas@tymate.com
 */
public interface BaseListCallback {

    void handleEmptyStateError(Throwable throwable);

    void handleHintError(String reason, String action, View.OnClickListener onClickListener);
}
