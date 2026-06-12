package co.istard.productapisimpledemo.Mapper;

import co.istard.productapisimpledemo.dto.CategoryRequest;
import co.istard.productapisimpledemo.dto.CategoryResponse;
import co.istard.productapisimpledemo.dto.UpdateCategoryRequest;
import co.istard.productapisimpledemo.entity.Category;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel="spring")
public interface CategoryMapper {
    //List Entity to List DTO
    List<CategoryResponse> toResponseList(List<Category> categories);
    //Entity to DTO
    CategoryResponse toResponse(Category category);
    //DTO to Entity
    Category toEntity(CategoryRequest request);

    //Update data
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(@MappingTarget Category category, UpdateCategoryRequest request);
}
