package com.kaiser.recipeappjava.base.activity;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.kaiser.recipeappjava.R;
import com.kaiser.recipeappjava.base.fragment.BaseMvpFragment;
import com.kaiser.recipeappjava.base.view.MvpView;
import com.kaiser.recipeappjava.libs.ui.FullScreenProgressDialog;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseMvpActivity extends AppCompatActivity implements MvpView, ActivityCompat.OnRequestPermissionsResultCallback {

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Call requestFeature before super.onCreate(savedInstanceState);

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

    protected void setFragment(BaseMvpFragment fragment) {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        switchFragment(fragment);
    }

    private void switchFragment(Fragment fragment) {
        this
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    protected void showProgressDialog() {
        FullScreenProgressDialog.show(this);
    }

    protected void hideProgressDialog() {
        FullScreenProgressDialog.hideProgressDialog();
    }

    @Override
    public void updateProgressDialog(boolean isShowProgressDialog) {
        if (isShowProgressDialog) {
            showProgressDialog();
        } else {
            hideProgressDialog();
        }
    }
}
