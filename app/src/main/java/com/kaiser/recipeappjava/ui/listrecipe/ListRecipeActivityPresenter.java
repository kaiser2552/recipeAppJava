package com.kaiser.recipeappjava.ui.listrecipe;

import com.kaiser.recipeappjava.base.presenter.BasePresenter;

import javax.inject.Inject;

public class ListRecipeActivityPresenter extends BasePresenter<ListRecipeActivityMvpView> {

    @Inject
    ListRecipeActivityPresenter() {

    }

    @Override
    public void attachView(ListRecipeActivityMvpView mvpView) {
        super.attachView(mvpView);
    }
}
