package co.istard.productapisimpledemo.service;

import co.istard.productapisimpledemo.dto.CategoryRequest;
import co.istard.productapisimpledemo.dto.CategoryResponse;
import co.istard.productapisimpledemo.dto.UpdateCategoryRequest;
import co.istard.productapisimpledemo.entity.Category;
import co.istard.productapisimpledemo.repository.CotegoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private  final CotegoryRepository categoryRepository;
    private static int nextId = 1003;
    private Category mapToEntity(CategoryResponse request){
        Category category = new Category();
        category.setName(request.name());
        category.setDescription(request.description());
        return category;
    }

    private  CategoryResponse mapToRespone(Category category){
        return  new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

    @Override
    public CategoryResponse createCategory(CategoryResponse category) {
        Category category1 = mapToEntity(category);
        category1.setId(nextId);
        return mapToRespone(categoryRepository.createCategory(category1));
    }

    @Override
    public List<CategoryResponse> findAllCategory() {
        return categoryRepository.cotegoryList()
                .stream()
                .map(this::mapToRespone)
                .toList();
    }

    @Override
    public CategoryResponse findCategoryById(Integer id) {
        var category = categoryRepository.findCategoryByid(id);
        if (category == null){
                log.info("Category is not found!!");
                return null;

        }
        return mapToRespone(category);
    }

    @Override
    public CategoryResponse updateCategoryById(Integer id, UpdateCategoryRequest request) {
        var existingCategory = categoryRepository.findCategoryByid(id);
        if (existingCategory == null){
            log.info("Category is not found!! ");
            return null;
        }
        if(request.name()!= null){
            existingCategory.setName(request.name());
        }
        if ((request.descript()!=null)){
            existingCategory.setDescription(request.descript());
        }
        categoryRepository.updateCotegory(existingCategory);
        return mapToRespone(existingCategory);
    }

    @Override
    public boolean deleteCategoryById(Integer id) {
        return categoryRepository.deleteCategoryById(id);
    }
}
