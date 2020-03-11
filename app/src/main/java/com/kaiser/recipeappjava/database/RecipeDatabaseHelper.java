package com.kaiser.recipeappjava.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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