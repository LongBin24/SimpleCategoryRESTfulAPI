package co.istard.productapisimpledemo.service;

import co.istard.productapisimpledemo.dto.ProductRequest;
import co.istard.productapisimpledemo.dto.ProductResponese;
import co.istard.productapisimpledemo.dto.UpdateProductRequest;
import co.istard.productapisimpledemo.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
//for
public interface ProductService {
    ProductResponese createProduct(ProductRequest product);
    List<ProductResponese> findAllProducts();
    ProductResponese findProductById(Integer id);
    ProductResponese updateProduct(Integer id, UpdateProductRequest request);
    boolean deleteProduct (int id);
}
