package com.kaiser.recipeappjava.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.kaiser.recipeappjava.R;
import com.kaiser.recipeappjava.model.RecipeModel;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class ListRecipeAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<RecipeModel> recipesList;

    public ListRecipeAdapter(Context context, ArrayList<RecipeModel> recipes) {
        mContext = context;
        recipesList = recipes;
    }

    @Override
    public int getCount() {
        return recipesList.size();
    }

    @Override
    public Object getItem(int position) {
        return recipesList.get(0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.list_recipe_view, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) convertView.getTag();
        }
        RecipeModel recipe = (RecipeModel) getItem(position);
        viewHolder.labelRecipeName.setText(recipe.getRecipeName());
        viewHolder.labelRecipeType.setText(recipe.getRecipeType());
        Picasso.with(mContext)
                .load(recipe.getRecipeImageURL())
                .placeholder(android.R.drawable.ic_menu_report_image)
                .error(android.R.drawable.ic_menu_report_image)
                .resize(
                        120,
                        120
                ).into(viewHolder.imageRecipe);

        viewHolder.labelIngredients.setText(recipe.getRecipeIngredients());
        return view;
    }

    private class ViewHolder {
        View row;

        ViewHolder(View row){
            this.row = row;
        }

        TextView labelRecipeName = row.findViewById(R.id.lbl_recipe_name);
        ImageView imageRecipe = row.findViewById(R.id.image_recipe);
        TextView labelRecipeType = row.findViewById(R.id.lbl_recipe_type);
        TextView labelIngredients = row.findViewById(R.id.lbl_recipe_ingredient);
    }
}
