package co.istard.productapisimpledemo.service;

import co.istard.productapisimpledemo.dto.CategoryRequest;
import co.istard.productapisimpledemo.dto.CategoryResponse;
import co.istard.productapisimpledemo.dto.UpdateCategoryRequest;
import co.istard.productapisimpledemo.entity.Category;
import org.springframework.data.domain.Page;

import java.util.Comparator;
import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest request);
    List<CategoryResponse> findAllCategory();
    CategoryResponse findCategoryById(Integer id);
    CategoryResponse updateCategoryById(Integer id, UpdateCategoryRequest request);
    void deleteCategoryById(Integer id);
    Page<CategoryResponse> findAllCategorys(int page,int size);
//    Gat all with pagination (follow product sample)
//    saft  delete category(changing the void of isDeleted)
}
