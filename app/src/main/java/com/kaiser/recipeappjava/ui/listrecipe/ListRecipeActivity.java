package com.kaiser.recipeappjava.ui.listrecipe;

import android.os.Bundle;

import com.kaiser.recipeappjava.AppApplication;
import com.kaiser.recipeappjava.R;
import com.kaiser.recipeappjava.base.activity.BaseMvpActivity;
import com.kaiser.recipeappjava.database.RecipeDatabaseHelper;
import com.kaiser.recipeappjava.model.RecipeModel;

import java.util.ArrayList;

public class ListRecipeActivity extends BaseMvpActivity implements ListRecipeActivityMvpView {

    private RecipeDatabaseHelper mDataHelper;

    public ListRecipeActivity(boolean hideToolbar) {
        super(hideToolbar);
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void injectAppComponent() {
        AppApplication.getAppComponent(this).inject(this);
    }

    @Override
    protected void setToolBar() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataHelper = new RecipeDatabaseHelper(this);
    }

    @Override
    public ArrayList<RecipeModel> getRecipes() {
        return mDataHelper.allRecipeList();
    }

    @Override
    public void recipeClicked() {

    }

    @Override
    public void showErrorMessageDialog(String errorTitle, String errorMessage, Boolean isBackLogin) {

    }
}
