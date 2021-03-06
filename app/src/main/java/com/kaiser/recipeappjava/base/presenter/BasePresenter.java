package com.kaiser.recipeappjava.base.presenter;

import android.content.Context;
import android.widget.Toast;

import com.kaiser.recipeappjava.base.view.MvpView;
import com.kaiser.recipeappjava.injection.context.ApplicationContext;
import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public class BasePresenter<T extends MvpView> implements Presenter<T> {
    @Inject
    @ApplicationContext
    protected Context mContext;

    protected CompositeSubscription mCompositeSubscription;
    private T mMvpView;

    @Inject
    public BasePresenter() {
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
            mCompositeSubscription.clear();
        }
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onResume() {
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public T getMvpView() {
        return mMvpView;
    }

    public void toast(Integer resId){
        Toast.makeText(this.mContext,resId, Toast.LENGTH_SHORT).show();
    }
}

