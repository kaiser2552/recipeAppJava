package com.kaiser.recipeappjava.ui.addrecipe;

import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.kaiser.recipeappjava.base.view.MvpView;
import com.kaiser.recipeappjava.model.RecipeModel;

public interface AddRecipeFragmentMvpView extends MvpView {

    void addRecipe(RecipeModel recipe);
    void switchFragment(Fragment fragment);
}
