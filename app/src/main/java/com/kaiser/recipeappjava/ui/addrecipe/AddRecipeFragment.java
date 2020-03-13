package com.kaiser.recipeappjava.ui.addrecipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.kaiser.recipeappjava.R;
import com.kaiser.recipeappjava.base.fragment.BaseMvpFragment;
import com.kaiser.recipeappjava.database.RecipeDatabaseHelper;
import com.kaiser.recipeappjava.model.RecipeModel;
import com.kaiser.recipeappjava.ui.listrecipe.ListRecipeActivity;
import com.kaiser.recipeappjava.ui.listrecipe.ListRecipeFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class AddRecipeFragment extends BaseMvpFragment implements AddRecipeFragmentMvpView {

    RecipeDatabaseHelper mDataHelper;
    @Inject
    AddRecipeFragmentPresenter mPresenter;

    @BindView(R.id.spinner_recipe_types)
    Spinner spinner_recipe_types;

    @BindView(R.id.et_recipe_name)
    EditText et_recipe_name;

    @BindView(R.id.et_recipe_image_link)
    EditText et_recipe_image_link;

    @BindView(R.id.et_recipe_ingredients)
    EditText et_recipe_ingredients;

    @BindView(R.id.et_recipe_steps)
    EditText et_recipe_steps;

    public static AddRecipeFragment newInstance() {
        AddRecipeFragment fragment = new AddRecipeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.attachView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDataHelper = new RecipeDatabaseHelper(getContext());

        initViews();
    }

    private void initViews(){

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_add_recipe;
    }

    @Override
    protected void injectAppComponent() {
        getAppComponent().inject(this);
    }

    @Override
    public void addRecipe(RecipeModel recipe) {
        Long result = mDataHelper.addRecipe(recipe);
        if(result > 0){
            toast(R.string.message_add_success);
            gotoHomeScreen();
        } else {
            toast(R.string.message_add_fail);
        }
    }

    private void toast(Integer resId){
        Toast.makeText(this.getContext(),resId, Toast.LENGTH_SHORT).show();
    }

    private void gotoHomeScreen(){
        ((ListRecipeActivity) getContext()).setFragmentToRoot(ListRecipeFragment.newInstance());
    }

    @OnClick(R.id.btn_add)
    void addRecipe(){
        RecipeModel recipe = new RecipeModel(
                et_recipe_name.getText().toString(),
                spinner_recipe_types.getSelectedItem().toString(),
                et_recipe_image_link.getText().toString(),
                et_recipe_ingredients.getText().toString(),
                et_recipe_steps.getText().toString());
        addRecipe(recipe);
    }

    @Override
    public void switchActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void switchFragment(Fragment fragment) {
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    @Override
    public void updateProgressDialog(boolean isShowProgressDialog) {
        if (isShowProgressDialog) {
            showProgressDialog();
        } else {
            hideProgressDialog();
        }
    }

    @Override
    public void showErrorMessageDialog(String errorTitle, String errorMessage, Boolean isBackLogin) {

    }
}
