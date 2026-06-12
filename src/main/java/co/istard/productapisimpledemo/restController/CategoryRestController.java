package co.istard.productapisimpledemo.restController;

import co.istard.productapisimpledemo.dto.CategoryRequest;
import co.istard.productapisimpledemo.dto.CategoryResponse;
import co.istard.productapisimpledemo.dto.UpdateCategoryRequest;
import co.istard.productapisimpledemo.entity.Category;
import co.istard.productapisimpledemo.service.CategoryService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryRestController {
    private final CategoryService service;

//    @GetMapping
//    public List<CategoryResponse> getAllCategory(){
//        return service.findAllCategory();
//    }

    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable Integer id){
        return service.findCategoryById(id);
    }

    @PostMapping
    public CategoryResponse creatCategory(@Valid @RequestBody CategoryRequest request){
        return service.createCategory(request);
    }

    @PatchMapping("/{id}")
    public CategoryResponse setCategoryById(@PathVariable Integer id, @RequestBody UpdateCategoryRequest request){
        return service.updateCategoryById(id,request);
    }
    @GetMapping
    public Page<CategoryResponse> getCategory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size

    ){
        return service.findAllCategorys(page,size);
    }
}
