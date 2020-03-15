package com.kaiser.recipeappjava.ui.listrecipe;

import androidx.fragment.app.Fragment;

import com.kaiser.recipeappjava.base.view.MvpView;
import com.kaiser.recipeappjava.model.RecipeModel;

import java.util.ArrayList;

public interface ListRecipeFragmentMvpView extends MvpView {
    ArrayList<RecipeModel> getRecipes();

    void recipeClicked(Integer position);

    void switchFragment(Fragment fragment);
}
