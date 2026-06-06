package co.istard.productapisimpledemo.service;

import co.istard.productapisimpledemo.dto.ProductRequest;
import co.istard.productapisimpledemo.dto.ProductResponese;
import co.istard.productapisimpledemo.dto.UpdateProductRequest;
import co.istard.productapisimpledemo.entity.Product;
import co.istard.productapisimpledemo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

    @Slf4j
    @Service
    @RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    //inject the repository here
    private final ProductRepository productRepository;
//    private final DefaultListCellRenderer defaultListCellRenderer;
    private static int nextId = 1004; 

    // mapToEntity
    private Product mapToEntity(ProductRequest request) {
        Product product = new Product();
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        return product;
    }

    // mapToResponse -> convert Entity to Response
    private ProductResponese mapToResponse(Product product){
        return new ProductResponese(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }

    @Override
    public ProductResponese createProduct(ProductRequest request) {
        //create entity product from the request
        Product product= mapToEntity(request);
        //set static userID
        product.setId(nextId++);
        product.setUserId(1);
        return mapToResponse(productRepository.createProduct(product));
    }

    @Override
    public List<ProductResponese> findAllProducts() {
        return productRepository.getAllProducts()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ProductResponese findProductById(Integer id) {
        var product = productRepository.findProductById(id);
        if(product == null){
            //throw not found exception, but skip it for now
            log.info("Product with id {} not found",id);
            return null;
        }
        return mapToResponse(product);
    }

    @Override
    public ProductResponese updateProduct(Integer id, UpdateProductRequest request) {
        //find existing product
        var existingProduct = productRepository.findProductById(id);

        if(existingProduct == null){
            log.info("Product with id {} not found",id);
            //throw exception
            return null;
        }
        if(request.name()!=null)
            existingProduct.setName(request.name());
        if(request.description()!=null){
            existingProduct.setDescription(request.description());
        }
        if (request.price()!=null){
            existingProduct.setPrice(request.price());
        }
        // Update product
        productRepository.updateProduct(existingProduct);

        return mapToResponse(existingProduct);

    }


    @Override
    public boolean deleteProduct(int id) {
        return productRepository.deleteProductById(id);
    }
}