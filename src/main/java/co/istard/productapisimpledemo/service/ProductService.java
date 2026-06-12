package co.istard.productapisimpledemo.service;

import co.istard.productapisimpledemo.dto.ProductRequest;
import co.istard.productapisimpledemo.dto.ProductResponese;
import co.istard.productapisimpledemo.dto.UpdateProductRequest;
import co.istard.productapisimpledemo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.awt.datatransfer.StringSelection;
import java.util.List;

public interface ProductService {
    ProductResponese createProduct(ProductRequest product);
    List<ProductResponese> findAllProducts();

//    for the pagination support when get all product
    Page<ProductResponese> findAllProct(String keyword,int page,int size);
//    Page<ProductResponse> name(String Keywords, pageable page)

    ProductResponese findProductById(Integer id);
    ProductResponese updateProduct(Integer id, UpdateProductRequest request);
    boolean deleteProduct (int id);
}
