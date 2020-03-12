package com.kaiser.recipeappjava.base.presenter;

import com.kaiser.recipeappjava.base.view.MvpView;

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 */
public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();

    void onStop();

    void onResume();
}
