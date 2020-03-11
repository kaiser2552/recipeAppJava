package com.kaiser.recipeappjava.base.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.kaiser.recipeappjava.base.view.MvpView;
import com.kaiser.recipeappjava.libs.ui.FullScreenProgressDialog;
import java.util.Calendar;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseMvpActivity extends AppCompatActivity implements MvpView, ActivityCompat.OnRequestPermissionsResultCallback {

    private static final long IDLE_TIMEOUT_MS = 15 * 60 * 1000; //milliseconds
    private final boolean hideToolbar;
    protected boolean isSessionExpired;
    private int apiCallCount = 0;

    private Unbinder unbinder;

    public BaseMvpActivity(boolean hideToolbar) {
        this.hideToolbar = hideToolbar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Call requestFeature before super.onCreate(savedInstanceState);
        if (!hideToolbar) {
            getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }

        super.onCreate(savedInstanceState);
        injectAppComponent();

        /**
         * DO NOT call bellow functions again from onCreate
         */
        setContentView(setLayoutResourceId());
        unbinder = ButterKnife.bind(this);
        setToolBar();
        /**
         * End DO NOT call again from onCreate
         */
        //block touch event when view is obscured
        //to prevent tapjacking
        View rootView = getWindow().getDecorView().getRootView();
        rootView.setFilterTouchesWhenObscured(true);
    }

    protected abstract int setLayoutResourceId();

    protected abstract void injectAppComponent();

    protected abstract void setToolBar();

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void resetIdleTimer() {
        isSessionExpired = false;
    }

    @Override
    public void onUserInteraction() {
        resetIdleTimer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    protected void showProgressDialog() {
        FullScreenProgressDialog.show(this);
    }

    protected void hideProgressDialog() {
        FullScreenProgressDialog.hideProgressDialog();
    }

    //for multiple api call on same page with single progress bar
    @Override
    public void updateApiCallCount(boolean start) {
        if (start) {
            this.apiCallCount += 1;
        } else {
            this.apiCallCount -= 1;
        }
    }

    @Override
    public void updateProgressDialog(boolean isShowProgressDialog) {
        if (isShowProgressDialog) {
            showProgressDialog();
        } else {
            if (apiCallCount == 0) {
                hideProgressDialog();
            }
        }
    }
}
