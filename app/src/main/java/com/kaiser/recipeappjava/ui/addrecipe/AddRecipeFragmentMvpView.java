package com.kaiser.recipeappjava.ui.addrecipe;

import androidx.fragment.app.Fragment;

import com.kaiser.recipeappjava.base.view.MvpView;
import com.kaiser.recipeappjava.model.RecipeModel;

public interface AddRecipeFragmentMvpView extends MvpView {
    void addRecipeAction(RecipeModel recipe);
    void switchFragment(Fragment fragment);
    void gotoHomeScreen();
}
