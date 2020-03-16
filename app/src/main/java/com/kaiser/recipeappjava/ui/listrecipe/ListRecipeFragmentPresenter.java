package com.kaiser.recipeappjava.ui.listrecipe;

import com.kaiser.recipeappjava.base.presenter.BasePresenter;
import com.kaiser.recipeappjava.database.RecipeDatabaseHelper;
import com.kaiser.recipeappjava.model.RecipeModel;

import java.util.ArrayList;

import javax.inject.Inject;

public class ListRecipeFragmentPresenter extends BasePresenter<ListRecipeFragmentMvpView> {

    private RecipeDatabaseHelper mDataHelper;

    @Inject
    ListRecipeFragmentPresenter() {

    }

    @Override
    public void attachView(ListRecipeFragmentMvpView mvpView) {
        super.attachView(mvpView);
        mDataHelper = new RecipeDatabaseHelper(this.mContext);
    }

    ArrayList<RecipeModel> getListRecipes() {
        return mDataHelper.allRecipeList();
    }
}
