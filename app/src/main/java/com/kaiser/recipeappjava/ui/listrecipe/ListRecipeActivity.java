package com.kaiser.recipeappjava.ui.listrecipe;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kaiser.recipeappjava.AppApplication;
import com.kaiser.recipeappjava.R;
import com.kaiser.recipeappjava.base.activity.BaseMvpActivity;
import com.kaiser.recipeappjava.base.fragment.BaseMvpFragment;
import com.kaiser.recipeappjava.ui.addrecipe.AddRecipeFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ListRecipeActivity extends BaseMvpActivity implements ListRecipeActivityMvpView {

    @Inject
    ListRecipeActivityPresenter mPresenter;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void injectAppComponent() {
        AppApplication.getAppComponent(this).inject(this);
    }

    @Override
    protected void setToolBar() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.attachView(this);

        fab.setOnClickListener(v -> switchFragment(AddRecipeFragment.newInstance()));

        if(savedInstanceState == null){
            switchFragment(ListRecipeFragment.newInstance());
        }
    }

    @Override
    public void showErrorMessageDialog(String errorTitle, String errorMessage, Boolean isBackLogin) {

    }

    @OnClick(R.id.fab)
    void addRecipe(){
        switchFragment(AddRecipeFragment.newInstance());
    }

    @Override
    public void switchActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void switchFragment(Fragment fragment) {
        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    public void setFragmentToRoot(BaseMvpFragment fragment) {
        setFragment(fragment);
    }
}
