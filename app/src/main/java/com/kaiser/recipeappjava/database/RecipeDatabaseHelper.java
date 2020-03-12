package com.kaiser.recipeappjava.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kaiser.recipeappjava.model.RecipeModel;

import java.util.ArrayList;

public class RecipeDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "recipe_database";
    private static final Integer DATABASE_VERSION = 1;
    private static final  String TABLE_RECIPES = "recipes";
    private static final String KEY_RECIPE_NAME = "name";
    private static final String KEY_RECIPE_TYPE = "type";
    private static final String KEY_RECIPE_IMAGE_URL = "url";
    private static final String KEY_RECIPE_INGREDIENTS = "ingredients";
    private static final String KEY_RECIPE_STEPS = "steps";

    public RecipeDatabaseHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public ArrayList<RecipeModel> allRecipeList() {
        ArrayList<RecipeModel>  recipeList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_RECIPES;

        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            RecipeModel recipe = new RecipeModel(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            recipeList.add(recipe);
            cursor.moveToNext();
        }
        return recipeList;
    }

    public Integer deleteRecipe(String recipeName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] recipe = {recipeName};
        return db.delete(TABLE_RECIPES, "$KEY_RECIPE_NAME=?", recipe);
    }

    public Long addRecipe(RecipeModel recipe) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_RECIPE_NAME, recipe.getRecipeName());
        values.put(KEY_RECIPE_TYPE, recipe.getRecipeType());
        values.put(KEY_RECIPE_IMAGE_URL, recipe.getRecipeImageURL());
        values.put(KEY_RECIPE_INGREDIENTS, recipe.getRecipeIngredients());
        values.put(KEY_RECIPE_STEPS, recipe.getRecipeStep());

        return db.insert(TABLE_RECIPES, null, values);
    }

    public Integer updateRecipe(RecipeModel recipe, String oldRecipeName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_RECIPE_NAME, recipe.getRecipeName());
        values.put(KEY_RECIPE_TYPE, recipe.getRecipeType());
        values.put(KEY_RECIPE_IMAGE_URL, recipe.getRecipeImageURL());
        values.put(KEY_RECIPE_INGREDIENTS, recipe.getRecipeIngredients());
        values.put(KEY_RECIPE_STEPS, recipe.getRecipeStep());
        String[] oldRecipe = {oldRecipeName};
        return db.update(TABLE_RECIPES, values, "$KEY_RECIPE_NAME=?", oldRecipe);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Execute script.
        /*CREATE TABLE students ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone_number TEXT......);*/
        String CREATE_TABLE_RECIPES = ("CREATE TABLE "
                + TABLE_RECIPES + "(" + KEY_RECIPE_NAME
                + " TEXT PRIMARY KEY," + KEY_RECIPE_TYPE + " TEXT," + KEY_RECIPE_IMAGE_URL + " TEXT ," + KEY_RECIPE_INGREDIENTS + " TEXT," + KEY_RECIPE_STEPS + " TEXT);");
        db.execSQL(CREATE_TABLE_RECIPES);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);

        // Recreate
        onCreate(db);
    }
}