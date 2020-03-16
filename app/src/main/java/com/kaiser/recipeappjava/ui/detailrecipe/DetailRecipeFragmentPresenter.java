package com.kaiser.recipeappjava.ui.detailrecipe;

import com.kaiser.recipeappjava.R;
import com.kaiser.recipeappjava.base.presenter.BasePresenter;
import com.kaiser.recipeappjava.database.RecipeDatabaseHelper;

import javax.inject.Inject;

public class DetailRecipeFragmentPresenter extends BasePresenter<DetailRecipeFragmentMvpView> {

    private RecipeDatabaseHelper mDataHelper;

    @Inject
    DetailRecipeFragmentPresenter() {

    }

    @Override
    public void attachView(DetailRecipeFragmentMvpView mvpView) {
        super.attachView(mvpView);
        mDataHelper = new RecipeDatabaseHelper(this.mContext);
    }

    void removeRecipe(String recipeName){
        Integer result = mDataHelper.deleteRecipe(recipeName);
        if(result > 0){
            toast(R.string.message_remove_success);
            getMvpView().gotoHomeScreen();
        } else {
            toast(R.string.message_remove_fail);
        }
    }
}
