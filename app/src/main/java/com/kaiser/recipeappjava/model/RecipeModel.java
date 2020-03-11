package com.kaiser.recipeappjava.model;

import android.os.Parcel;
import android.os.Parcelable;

public class RecipeModel implements Parcelable {
    private String recipeName = "";
    private String recipeImageURL = "";
    private String recipeType = "";
    private String recipeIngredients = "";
    private String recipeStep = "";

    protected RecipeModel(Parcel in) {
        recipeName = in.readString();
        recipeImageURL = in.readString();
        recipeType = in.readString();
        recipeIngredients = in.readString();
        recipeStep = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(recipeName);
        dest.writeString(recipeImageURL);
        dest.writeString(recipeType);
        dest.writeString(recipeIngredients);
        dest.writeString(recipeStep);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RecipeModel> CREATOR = new Creator<RecipeModel>() {
        @Override
        public RecipeModel createFromParcel(Parcel in) {
            return new RecipeModel(in);
        }

        @Override
        public RecipeModel[] newArray(int size) {
            return new RecipeModel[size];
        }
    };
}