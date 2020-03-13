package com.kaiser.recipeappjava.ui.listrecipe;

import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.kaiser.recipeappjava.base.view.MvpView;

public interface ListRecipeActivityMvpView extends MvpView {
    void switchActivity(Intent intent);

    void switchFragment(Fragment fragment);
}
