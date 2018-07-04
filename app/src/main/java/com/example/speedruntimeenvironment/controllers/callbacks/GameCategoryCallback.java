package com.example.speedruntimeenvironment.controllers.callbacks;

import com.example.speedruntimeenvironment.model.Category;

import java.util.List;

public interface GameCategoryCallback {
    void onGetCategoriesSuccess(List<Category> categories);
}
