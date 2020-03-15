package com.kaiser.recipeappjava.ui.detailrecipe;

import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.kaiser.recipeappjava.base.view.MvpView;
import com.kaiser.recipeappjava.model.RecipeModel;

public interface DetaillRecipeFragmentMvpView extends MvpView {

    void editRecipe(RecipeModel recipe);

    void removeRecipe(String recipeName);

    void switchFragment(Fragment fragment);
}
