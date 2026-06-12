package co.istard.productapisimpledemo.service;

import co.istard.productapisimpledemo.Mapper.CategoryMapper;
import co.istard.productapisimpledemo.dto.CategoryRequest;
import co.istard.productapisimpledemo.dto.CategoryResponse;
import co.istard.productapisimpledemo.dto.UpdateCategoryRequest;
import co.istard.productapisimpledemo.entity.Category;
import co.istard.productapisimpledemo.repository.CotegoryRepository;
import co.istard.productapisimpledemo.repository.CotegoryRepositoryOld;
import co.istard.productapisimpledemo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Line;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private  final CotegoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ProductRepository productRepository;

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        // ប្រើ MapStruct បម្លែង Response/Request ទៅជា Entity
        Category category = categoryMapper.toEntity(request);
        var newCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(newCategory);
    }

    @Override
    public List<CategoryResponse> findAllCategory() {
        // Mapper convert to List
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toResponseList(categories);
    }

    @Override
    public CategoryResponse findCategoryById(Integer id) {

        Category category = categoryRepository.findById(id).orElseThrow(()->new NoSuchElementException("Category with id:"+id+"not found!!"));
        return categoryMapper.toResponse(category);
    }

    @Override
    public CategoryResponse updateCategoryById(Integer id, UpdateCategoryRequest request) {
        var existingCategory = categoryRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Category with ID = "+id+" not found"));;

        //Use mapStruct to update field
        categoryMapper.updateEntityFromRequest(existingCategory, request);
        //save data
        categoryRepository.save(existingCategory);

        return categoryMapper.toResponse(existingCategory);
    }

    @Override
    public void deleteCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Product with id = \" + id + \" does not exist"));
//        if(!productRepository.existsById(id)) {
//            throw new NoSuchElementException("");
//        }
        category.setIsDeleted(true);
        categoryRepository.save(category);
    }

    @Override
    public Page<CategoryResponse> findAllCategorys(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);

        return categoryRepository.findAll(pageable).map(categoryMapper::toResponse);
    }

}
