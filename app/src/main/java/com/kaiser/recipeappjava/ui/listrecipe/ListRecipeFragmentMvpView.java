package com.kaiser.recipeappjava.ui.listrecipe;

import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.kaiser.recipeappjava.base.view.MvpView;
import com.kaiser.recipeappjava.model.RecipeModel;

import java.util.ArrayList;

public interface ListRecipeFragmentMvpView extends MvpView {
    ArrayList<RecipeModel> getRecipes();
    void recipeClicked();

    void addRecipeClicked();

    void switchActivity(Intent intent);

    void switchFragment(Fragment fragment);
}
