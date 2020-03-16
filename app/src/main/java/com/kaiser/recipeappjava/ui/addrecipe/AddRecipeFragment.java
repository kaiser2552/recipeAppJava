package com.kaiser.recipeappjava.ui.addrecipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.fragment.app.Fragment;

import com.kaiser.recipeappjava.R;
import com.kaiser.recipeappjava.base.fragment.BaseMvpFragment;
import com.kaiser.recipeappjava.model.RecipeModel;
import com.kaiser.recipeappjava.ui.listrecipe.ListRecipeActivity;
import com.kaiser.recipeappjava.ui.listrecipe.ListRecipeFragment;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class AddRecipeFragment extends BaseMvpFragment implements AddRecipeFragmentMvpView {
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
        return new AddRecipeFragment();
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

        initViews();
    }

    private void initViews() {
        showAddRecipeButton(false);

        String[] recipeTypes = getResources().getStringArray(R.array.recipe_types);

        @SuppressWarnings("unchecked")
        ArrayAdapter adapter = new ArrayAdapter(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, recipeTypes);
        spinner_recipe_types.setAdapter(adapter);
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
    public void addRecipeAction(RecipeModel recipe) {
        mPresenter.addRecipe(recipe);
    }

    @OnClick(R.id.btn_add)
    void addRecipe() {
        if (et_recipe_name.getText().toString().equals("") || spinner_recipe_types.getSelectedItem().toString().equals("")
                || et_recipe_image_link.getText().toString().equals("") || et_recipe_steps.getText().toString().equals("")
                || et_recipe_ingredients.getText().toString().equals("")) {
            mPresenter.toast(R.string.message_warning_lost_info);
        } else {
            RecipeModel recipe = new RecipeModel(
                    et_recipe_name.getText().toString(),
                    spinner_recipe_types.getSelectedItem().toString(),
                    et_recipe_image_link.getText().toString(),
                    et_recipe_ingredients.getText().toString(),
                    et_recipe_steps.getText().toString());
            addRecipeAction(recipe);
        }
    }

    @Override
    public void switchFragment(Fragment fragment) {
        Objects.requireNonNull(getActivity())
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    @Override
    public void gotoHomeScreen() {
        ((ListRecipeActivity) Objects.requireNonNull(getContext())).setFragmentToRoot(ListRecipeFragment.newInstance());
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
