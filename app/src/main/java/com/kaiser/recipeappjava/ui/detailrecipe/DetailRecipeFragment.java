package com.kaiser.recipeappjava.ui.detailrecipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.kaiser.recipeappjava.R;
import com.kaiser.recipeappjava.base.fragment.BaseMvpFragment;
import com.kaiser.recipeappjava.database.RecipeDatabaseHelper;
import com.kaiser.recipeappjava.model.RecipeModel;
import com.kaiser.recipeappjava.ui.editrecipe.EditRecipeFragment;
import com.kaiser.recipeappjava.ui.listrecipe.ListRecipeActivity;
import com.kaiser.recipeappjava.ui.listrecipe.ListRecipeFragment;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class DetailRecipeFragment extends BaseMvpFragment implements DetaillRecipeFragmentMvpView {

    private RecipeDatabaseHelper mDataHelper;
    private RecipeModel recipe;

    @Inject
    DetailRecipeFragmentPresenter mPresenter;

    @BindView(R.id.recipe_name)
    TextView recipe_name;

    @BindView(R.id.recipe_type)
    TextView recipe_type;

    @BindView(R.id.recipe_thumbnail)
    ImageView recipe_thumbnail;

    @BindView(R.id.recipe_steps_detail)
    TextView recipe_steps_detail;

    @BindView(R.id.recipe_ingredients)
    TextView recipe_ingredients;

    public static DetailRecipeFragment newInstance(RecipeModel recipe) {
        return new DetailRecipeFragment(recipe);
    }

    private DetailRecipeFragment(RecipeModel recipe){
        this.recipe = recipe;
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
        showAddRecipeButton(false);

        recipe_name.setText(recipe.getRecipeName());
        recipe_type.setText(recipe.getRecipeType());
        Picasso.with(getContext()).load(recipe.getRecipeImageURL()).into(recipe_thumbnail);
        recipe_steps_detail.setText(recipe.getRecipeStep());
        recipe_ingredients.setText(recipe.getRecipeIngredients());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_recipe_detail;
    }

    @Override
    protected void injectAppComponent() {
        getAppComponent().inject(this);
    }

    private void toast(Integer resId){
        Toast.makeText(this.getContext(),resId, Toast.LENGTH_SHORT).show();
    }

    private void gotoHomeScreen(){
        ((ListRecipeActivity) Objects.requireNonNull(getContext())).setFragmentToRoot(ListRecipeFragment.newInstance());
    }

    @OnClick(R.id.btn_edit)
    void editRecipe(){
        editRecipe(recipe);
    }

    @OnClick(R.id.btn_go_back)
    void goBack(){
        onBackPressed();
    }

    @OnClick(R.id.btn_remove)
    void removeRecipe(){
        removeRecipe(recipe.getRecipeName());
    }

    @Override
    public void editRecipe(RecipeModel recipe) {
        switchFragment(EditRecipeFragment.newInstance(recipe));
    }

    @Override
    public void removeRecipe(String recipeName) {
        Integer result = mDataHelper.deleteRecipe(recipeName);
        if(result > 0){
            toast(R.string.message_remove_success);
            gotoHomeScreen();
        } else {
            toast(R.string.message_remove_fail);
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
