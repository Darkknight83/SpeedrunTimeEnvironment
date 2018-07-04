package com.example.speedruntimeenvironment.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategoryList {
    List<Category> categories;

    public CategoryList() {
        this.categories = new ArrayList<>();
    }

    public void addCategory(Category cat) {
        this.categories.add(cat);
    }


    public static CategoryList fromJson(JSONObject obj) throws JSONException{
        CategoryList categoryList = new CategoryList();

        JSONArray data = obj.getJSONArray("data");

        for(int i = 0; i < data.length(); i++) {
            Category category = Category.fromJson(data.getJSONObject(i));
            categoryList.addCategory(category);
        }
        return categoryList;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
