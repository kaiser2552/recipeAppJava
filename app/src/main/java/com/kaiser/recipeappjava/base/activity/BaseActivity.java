package com.kaiser.recipeappjava.base.activity;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
