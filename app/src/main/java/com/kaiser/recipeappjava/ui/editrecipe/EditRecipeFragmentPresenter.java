package com.kaiser.recipeappjava.ui.editrecipe;

import com.kaiser.recipeappjava.R;
import com.kaiser.recipeappjava.base.presenter.BasePresenter;
import com.kaiser.recipeappjava.database.RecipeDatabaseHelper;
import com.kaiser.recipeappjava.model.RecipeModel;

import javax.inject.Inject;

public class EditRecipeFragmentPresenter extends BasePresenter<EditRecipeFragmentMvpView> {

    private RecipeDatabaseHelper mDataHelper;

    @Inject
    EditRecipeFragmentPresenter() {

    }

    @Override
    public void attachView(EditRecipeFragmentMvpView mvpView) {
        super.attachView(mvpView);
        mDataHelper = new RecipeDatabaseHelper(this.mContext);
    }

    void updateRecipe(RecipeModel recipe, String currentRecipeName) {
        Integer result = mDataHelper.updateRecipe(recipe, currentRecipeName);
        if(result > 0){
            toast(R.string.message_update_success);
            getMvpView().gotoHomeScreen();
        } else {
            toast(R.string.message_update_fail);
        }
    }
}
