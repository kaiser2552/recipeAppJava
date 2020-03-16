package com.kaiser.recipeappjava.ui.editrecipe;

import androidx.fragment.app.Fragment;

import com.kaiser.recipeappjava.base.view.MvpView;
import com.kaiser.recipeappjava.model.RecipeModel;

public interface EditRecipeFragmentMvpView extends MvpView {
    void updateRecipe(RecipeModel recipe);
    void switchFragment(Fragment fragment);
    void gotoHomeScreen();
}
