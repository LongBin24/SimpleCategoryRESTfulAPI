package co.istard.productapisimpledemo.repository;

import co.istard.productapisimpledemo.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class ProductRepository {
    private final List<Product> productList = new ArrayList<>();

    public ProductRepository() {
        productList.add(new Product(1001, "Cocacola", "Nice when cool", 1.20f, 101));
        productList.add(new Product(1002, "Sting", "Unlimited Sweetness", 1.5f, 102));
        productList.add(new Product(1003, "Fanta", "So cool and fresh!", 2.00f, 103));
    }

    //Get All of product
    public  List<Product> getAllProducts(){
        return productList;
    }
    //Create Product
    public Product createProduct(Product product){
        productList.add(product);
        return product;
    }

    //Find Product By Id
    public Product findProductById(Integer id){
        return productList.stream()
                .filter(product -> product.getId()==id)
                .findFirst()
                .orElseThrow(() ->new NoSuchElementException("Product with ID: "+id+"Not Found!!"));
    }

    //DeleteById
    public boolean deleteProductById(Integer id){
        return productList
                .removeIf(product -> product.getId() ==id);
    }

    // update product by id
    public Product updateProduct(Product updateProduct ){
        for(int i = 0 ; i<productList.size(); i++){
            var product = productList.get(i);
            if(product.getId() == updateProduct.getId()){
                productList.set(i, updateProduct);
                return updateProduct;
            }
        }
        return null;
    }
}
