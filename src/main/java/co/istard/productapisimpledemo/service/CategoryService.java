package co.istard.productapisimpledemo.service;

import co.istard.productapisimpledemo.dto.CategoryResponse;
import co.istard.productapisimpledemo.dto.UpdateCategoryRequest;
import co.istard.productapisimpledemo.entity.Category;

import java.util.Comparator;
import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryResponse     category);
    List<CategoryResponse> findAllCategory();
    CategoryResponse findCategoryById(Integer id);
    CategoryResponse updateCategoryById(Integer id, UpdateCategoryRequest request);
    boolean deleteCategoryById(Integer id);
}
