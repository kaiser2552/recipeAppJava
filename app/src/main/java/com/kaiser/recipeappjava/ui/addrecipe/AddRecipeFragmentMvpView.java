package com.kaiser.recipeappjava.ui.addrecipe;

import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.kaiser.recipeappjava.base.view.MvpView;
import com.kaiser.recipeappjava.model.RecipeModel;

import java.util.ArrayList;

public interface AddRecipeFragmentMvpView extends MvpView {

    void addRecipe(RecipeModel recipe);

    void switchActivity(Intent intent);

    void switchFragment(Fragment fragment);
}
