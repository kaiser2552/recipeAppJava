package com.kaiser.recipeappjava.ui.editrecipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.kaiser.recipeappjava.R;
import com.kaiser.recipeappjava.base.fragment.BaseMvpFragment;
import com.kaiser.recipeappjava.database.RecipeDatabaseHelper;
import com.kaiser.recipeappjava.model.RecipeModel;
import com.kaiser.recipeappjava.ui.listrecipe.ListRecipeActivity;
import com.kaiser.recipeappjava.ui.listrecipe.ListRecipeFragment;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class EditRecipeFragment extends BaseMvpFragment implements EditRecipeFragmentMvpView {

    private RecipeDatabaseHelper mDataHelper;
    private String currentRecipeName;
    private RecipeModel currentRecipe;

    @Inject
    EditRecipeFragmentPresenter mPresenter;

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

    @BindView(R.id.top_image)
    ImageView top_image;

    @BindView(R.id.btn_add)
    Button btn_add;

    public static EditRecipeFragment newInstance(RecipeModel recipe) {
        return new EditRecipeFragment(recipe);
    }

    private EditRecipeFragment(RecipeModel recipe){
        currentRecipe = recipe;
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

        String[] recipeTypes = getResources().getStringArray(R.array.recipe_types);

        @SuppressWarnings("unchecked")
        ArrayAdapter adapter = new ArrayAdapter(Objects.requireNonNull(this.getContext()),android.R.layout.simple_spinner_item, recipeTypes);
        spinner_recipe_types.setAdapter(adapter);

        currentRecipeName = currentRecipe.getRecipeName();

        btn_add.setText(R.string.label_update);

        //add info
        et_recipe_name.setText(currentRecipe.getRecipeName());
        et_recipe_image_link.setText(currentRecipe.getRecipeImageURL());
        et_recipe_ingredients.setText(currentRecipe.getRecipeIngredients());
        et_recipe_steps.setText(currentRecipe.getRecipeStep());
        Picasso.with(getContext()).load(currentRecipe.getRecipeImageURL()).into(top_image);

        int spinnerPosition = adapter.getPosition(currentRecipe.getRecipeType());
        spinner_recipe_types.setSelection(spinnerPosition);
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
    public void updateRecipe(RecipeModel recipe) {
        Integer result = mDataHelper.updateRecipe(recipe, currentRecipeName);
        if(result > 0){
            toast(R.string.message_update_success);
            gotoHomeScreen();
        } else {
            toast(R.string.message_update_fail);
        }
    }

    private void toast(Integer resId){
        Toast.makeText(this.getContext(),resId, Toast.LENGTH_SHORT).show();
    }

    private void gotoHomeScreen(){
        ((ListRecipeActivity) Objects.requireNonNull(getContext())).setFragmentToRoot(ListRecipeFragment.newInstance());
    }

    @OnClick(R.id.btn_add)
    void updateRecipe(){
        if (et_recipe_name.getText().toString().equals("") || spinner_recipe_types.getSelectedItem().toString().equals("")
                || et_recipe_image_link.getText().toString().equals("") || et_recipe_steps.getText().toString().equals("")
                || et_recipe_ingredients.getText().toString().equals("")) {
            toast(R.string.message_warning_lost_info);
        } else {
            RecipeModel recipe = new RecipeModel(
                    et_recipe_name.getText().toString(),
                    spinner_recipe_types.getSelectedItem().toString(),
                    et_recipe_image_link.getText().toString(),
                    et_recipe_ingredients.getText().toString(),
                    et_recipe_steps.getText().toString());
            updateRecipe(recipe);
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
