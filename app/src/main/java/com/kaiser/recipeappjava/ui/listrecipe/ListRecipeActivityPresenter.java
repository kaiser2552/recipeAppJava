package com.kaiser.recipeappjava.ui.listrecipe;

import com.kaiser.recipeappjava.R;
import com.kaiser.recipeappjava.base.activity.BaseMvpActivity;

public class ListRecipeActivityPresenter extends BaseMvpActivity implements ListRecipeActivityMvpView {

    public ListRecipeActivityPresenter(boolean hideToolbar) {
        super(hideToolbar);
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_list_recipe;
    }

    @Override
    protected void injectAppComponent() {

    }

    @Override
    protected void setToolBar() {

    }

    @Override
    public void getRecipes() {

    }

    @Override
    public void recipeClicked() {

    }

    @Override
    public void showErrorMessageDialog(String errorTitle, String errorMessage, Boolean isBackLogin) {

    }
}
