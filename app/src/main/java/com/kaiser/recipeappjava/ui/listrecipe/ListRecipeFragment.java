package com.kaiser.recipeappjava.ui.listrecipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.kaiser.recipeappjava.R;
import com.kaiser.recipeappjava.adapter.ListRecipeAdapter;
import com.kaiser.recipeappjava.base.fragment.BaseMvpFragment;
import com.kaiser.recipeappjava.model.RecipeModel;
import com.kaiser.recipeappjava.ui.detailrecipe.DetailRecipeFragment;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;

public class ListRecipeFragment extends BaseMvpFragment implements ListRecipeFragmentMvpView {

    @Inject
    ListRecipeFragmentPresenter mPresenter;

    @BindView(R.id.list_recipe)
    ListView list_recipe;

    public static ListRecipeFragment newInstance() {
        return new ListRecipeFragment();
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

    private void initViews(){
        showAddRecipeButton(true);

        list_recipe.setAdapter(new ListRecipeAdapter(this.getContext(), getRecipes()));
        list_recipe.setOnItemClickListener((parent, view, position, id) -> recipeClicked(position));
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_list_recipe;
    }

    @Override
    protected void injectAppComponent() {
        getAppComponent().inject(this);
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

    @Override
    public ArrayList<RecipeModel> getRecipes() {
        return mPresenter.getListRecipes();
    }

    @Override
    public void recipeClicked(Integer position) {
        switchFragment(DetailRecipeFragment.newInstance(getRecipes().get(position)));
    }
}
