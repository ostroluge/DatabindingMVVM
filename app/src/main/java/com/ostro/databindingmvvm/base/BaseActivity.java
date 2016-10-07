package com.ostro.databindingmvvm.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.ostro.databindingmvvm.R;

import timber.log.Timber;

public abstract class BaseActivity extends AppCompatActivity {

    protected AppBarLayout mAppBarLayout;
    protected Toolbar mToolbar;

    private Snackbar mSnackBar;
    private Bundle mSavedInstanceState;

    public abstract void injectDependencies();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        injectDependencies();
        super.onCreate(savedInstanceState);
        mSavedInstanceState = savedInstanceState;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onHomeClicked();
            return true;
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        try {
            return super.dispatchTouchEvent(ev);
        } catch (Exception e) {
            Timber.e(e, "dispatchTouchEvent");
            return false;
        }
    }

    public static BaseActivity get(Fragment fragment) {
        return (BaseActivity) fragment.getActivity();
    }

    protected void initView() {
        mToolbar = getToolbar();
        mAppBarLayout = getAppBarLayout();

        if (mToolbar == null) {
            Timber.w("onCreate but there is not toolbar");
        }
        initToolbar(mToolbar);
    }

    public void initToolbar(@Nullable Toolbar toolbar) {
        if (toolbar == null) {
            return;
        }
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            initActionBar(actionBar);
        }
    }

    public Toolbar getToolbar() {
        if(mToolbar == null) {
            mToolbar = (Toolbar) this.findViewById(R.id.main_toolbar);
        }
        return mToolbar;
    }

    public AppBarLayout getAppBarLayout() {
        if(mAppBarLayout == null) {
            mAppBarLayout = (AppBarLayout) this.findViewById(R.id.app_bar_layout);
        }
        return mAppBarLayout;
    }

    public void setTitle(String title) {
        if (getToolbar() != null) {
            mToolbar.setTitle(title);
        } else {
            Timber.e("trying to put title %s on null toolbar", title);
        }
    }

    public Bundle getSavedInstanceState() {
        return mSavedInstanceState;
    }

    public boolean hasSavedState() {
        return mSavedInstanceState != null;
    }

    public void initActionBar(ActionBar actionBar) {
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    protected int tryPopStack() {
        int backStackCount = getBackstackCount();
        if (backStackCount > 0) {
            getSupportFragmentManager().popBackStack();
            return backStackCount;
        }
        return 0;
    }

    public int getBackstackCount() {
        return getSupportFragmentManager().getBackStackEntryCount();
    }

    public void errorSnackBar(View view, String message) {
        if (view == null) {
            return;
        }
        mSnackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        mSnackBar.show();
    }

    public void errorSnackBar(View view, String message, String actionMsg, View.OnClickListener onClickListener) {
        errorSnackBar(view, message, actionMsg, onClickListener, Snackbar.LENGTH_LONG);
    }

    public void errorSnackBar(View view, String message, String actionMsg, View.OnClickListener onClickListener, int duration) {
        if (view == null) {
            return;
        }
        mSnackBar = Snackbar.make(view, message, duration).setAction(actionMsg, onClickListener);
        mSnackBar.show();
    }

    public void hideSnackBar() {
        if (mSnackBar != null) {
            mSnackBar.dismiss();
        }
    }

    protected void onHomeClicked() {
        if (tryPopStack() <= 0) {
            finish();
        }
    }

    public boolean replaceFragmentWithBackStack(int container, String name, Fragment fragment) {
        return replaceFragmentWithBackStack(container, name, fragment, 0, 0, 0, 0);
    }

    public boolean replaceFragmentWithBackStack(int container, String name, Fragment fragment,
                                                int animEnter, int animExit, int animPopEnter, int animPopExit) {
        return replaceFragment(container, name, fragment, true, animEnter, animExit, animPopEnter, animPopExit);
    }

    public boolean replaceFragment(int container, String name, Fragment fragment) {
        return replaceFragment(container, name, fragment, 0, 0, 0, 0);
    }

    public boolean replaceFragment(int container, String name, Fragment fragment,
                                   int animEnter, int animExit, int animPopEnter, int animPopExit) {
        return replaceFragment(container, name, fragment, false, animEnter, animExit, animPopEnter, animPopExit);
    }

    public boolean replaceFragment(int container, String name, Fragment fragment, boolean backStack,
                                   int animEnter, int animExit, int animPopEnter, int animPopExit) {
        try {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                    .beginTransaction();
            fragmentTransaction.setCustomAnimations(animEnter, animExit, animPopEnter, animPopExit);
            if (backStack) {
                fragmentTransaction.addToBackStack(name);
            }
            fragmentTransaction.replace(container, fragment).commit();
        } catch (Exception e) {
            Timber.e(e, "replace fragment");
            return false;
        }
        return true;
    }
}