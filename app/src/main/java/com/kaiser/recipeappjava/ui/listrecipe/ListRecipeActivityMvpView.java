package com.kaiser.recipeappjava.ui.listrecipe;

import com.kaiser.recipeappjava.base.view.MvpView;
import com.kaiser.recipeappjava.model.RecipeModel;

import java.util.ArrayList;

public interface ListRecipeActivityMvpView extends MvpView {
    ArrayList<RecipeModel> getRecipes();
    void recipeClicked();
}
