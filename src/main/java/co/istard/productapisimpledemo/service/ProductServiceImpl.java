package co.istard.productapisimpledemo.service;

import co.istard.productapisimpledemo.Mapper.ProductMapper;
import co.istard.productapisimpledemo.dto.ProductRequest;
import co.istard.productapisimpledemo.dto.ProductResponese;
import co.istard.productapisimpledemo.dto.UpdateProductRequest;
import co.istard.productapisimpledemo.entity.Product;
import co.istard.productapisimpledemo.repository.ProductRepository;
import co.istard.productapisimpledemo.repository.ProductRepositoryOld;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
    @Service
    @RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    //inject the repository here
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
//    private final DefaultListCellRenderer defaultListCellRenderer;
//    private static int nextId = 1004;

//    // mapToEntity
//    private Product mapToEntity(ProductRequest request) {
//        Product product = new Product();
//        product.setName(request.name());
//        product.setDescription(request.description());
//        product.setPrice(request.price());
//        return product;
//    }
//
//    // mapToResponse -> convert Entity to Response
//    private ProductResponese mapToResponse(Product product){
//        return new ProductResponese(
//                product.getId(),
//                product.getName(),
//                product.getDescription(),
//                product.getPrice()
//        );
//    }

    @Override
    public ProductResponese createProduct(ProductRequest request) {
        //create entity product from the request
        Product product= productMapper.mapToProduct(request);
        //set static userID
//        product.setId(nextId++);
        product.setUserId(1);
        return  productMapper.mapToResponse(productRepository.save(product));
    }

    @Override
    public List<ProductResponese> findAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::mapToResponse)
                .toList();
    }

    @Override
    public Page<ProductResponese> findAllProct(String keyword,int page,int size) {
        Pageable pageable = PageRequest.of(page,size);
        return productRepository.findByNameContainingIgnoreCase(keyword,pageable)
                .map(productMapper::mapToResponse);
    }

    @Override
    public ProductResponese findProductById(Integer id) {
//        var product = productRepository.findById(id);
//        if(product == null){
//            //throw not found exception, but skip it for now
//            log.info("Product with id {} not found",id);
//            return null;
//        }
//        return mapToResponse(product);

        var product =  productRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Product with ID = "+id+" not found"));

        return productMapper.mapToResponse(product);
    }

    @Override
    public ProductResponese updateProduct(Integer id, UpdateProductRequest request) {
        //find existing product
        var existingProduct =productRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Product with ID = "+id+" not found"));

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
        productRepository.save(existingProduct);

        return productMapper.mapToResponse( existingProduct);

    }

    @Override
    public boolean deleteProduct(int id) {
        // find if the product exist
        // if it's we delete it and return true
        // else return false

        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}