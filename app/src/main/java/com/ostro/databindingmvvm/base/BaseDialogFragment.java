package com.ostro.databindingmvvm.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;

/**
 * Created by Nicolas Dumont
 * nicolas@tymate.com
 */
public abstract class BaseDialogFragment extends DialogFragment {

    private boolean stop = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        injectDependencies((BaseActivity) context);
    }

    @Override
    public void onViewCreated(View view, Bundle inState) {
        super.onViewCreated(view, inState);
    }

    protected abstract void injectDependencies(BaseActivity baseActivity);

    @Override
    public void onStart() {
        super.onStart();
        stop = false;
    }

    @Override
    public void onStop() {
        super.onStop();
        stop = true;
    }

    public boolean isStop() {
        return stop;
    }

    public void errorSnackBar(View view, String message) {
        BaseActivity.get(this).errorSnackBar(view, message);
    }

    public void errorSnackBar(View view, String message, String actionMsg, View.OnClickListener onClickListener) {
        BaseActivity.get(this).errorSnackBar(view, message, actionMsg, onClickListener);
    }

    public void errorSnackBar(View view, String message, String actionMsg, View.OnClickListener onClickListener, int time) {
        BaseActivity.get(this).errorSnackBar(view, message, actionMsg, onClickListener, time);
    }

    public void hideSnackBar() {
        BaseActivity.get(this).hideSnackBar();
    }
}