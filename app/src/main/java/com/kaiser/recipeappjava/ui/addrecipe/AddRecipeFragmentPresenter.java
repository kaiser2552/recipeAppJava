package com.kaiser.recipeappjava.ui.addrecipe;

import com.kaiser.recipeappjava.R;
import com.kaiser.recipeappjava.base.presenter.BasePresenter;
import com.kaiser.recipeappjava.database.RecipeDatabaseHelper;
import com.kaiser.recipeappjava.model.RecipeModel;

import javax.inject.Inject;

public class AddRecipeFragmentPresenter extends BasePresenter<AddRecipeFragmentMvpView> {

    private RecipeDatabaseHelper mDataHelper;

    @Inject
    AddRecipeFragmentPresenter() {

    }

    @Override
    public void attachView(AddRecipeFragmentMvpView mvpView) {
        super.attachView(mvpView);
        mDataHelper = new RecipeDatabaseHelper(this.mContext);
    }

    void addRecipe(RecipeModel recipe) {
        Long result = mDataHelper.addRecipe(recipe);
        if (result > 0) {
            toast(R.string.message_add_success);
            getMvpView().gotoHomeScreen();
        } else {
            toast(R.string.message_add_fail);
        }
    }
}
