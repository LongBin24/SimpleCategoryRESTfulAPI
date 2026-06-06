package co.istard.productapisimpledemo.repository;

import co.istard.productapisimpledemo.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class CotegoryRepository {
    private final List<Category> categories = new ArrayList<>();
    public  CotegoryRepository(){
        categories.add(new Category(1001,"Electronic","Electronic Divide"));
        categories.add(new Category(1002,"Food","Daily food and drink"));
    }
    public List<Category> cotegoryList(){
        return categories;
    }
    public Category createCategory(Category category){
        categories.add(category);
        return category;
    }

    //Find Category By id
    public Category findCategoryByid(Integer id){
        return cotegoryList().stream()
                .filter(category -> category.getId()==id)
                .findFirst()
                .orElseThrow(()->new NoSuchElementException("Cotegory with id:"+id+"not found"));
    }

    //Delete Category by id
    public boolean deleteCategoryById(Integer id){
        return cotegoryList().removeIf(category -> category.getId()==id );
    }

    //Update cotegory by id
    public Category updateCotegory(Category update){
        for (int i=0;i<cotegoryList().size();i++){
            var category = cotegoryList().get(i);
            if (category.getId() == update.getId()){
                cotegoryList().set(i,update);
                return update;
            }

        }
        return null;
    }
}
