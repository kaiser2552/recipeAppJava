package com.kaiser.recipeappjava.ui.listrecipe;

import androidx.fragment.app.Fragment;

import com.kaiser.recipeappjava.base.view.MvpView;

public interface ListRecipeActivityMvpView extends MvpView {
    void switchFragment(Fragment fragment);
}
