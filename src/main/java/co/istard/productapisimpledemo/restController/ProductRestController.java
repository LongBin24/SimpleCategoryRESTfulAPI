package co.istard.productapisimpledemo.restController;

import co.istard.productapisimpledemo.dto.ProductRequest;
import co.istard.productapisimpledemo.dto.ProductResponese;
import co.istard.productapisimpledemo.dto.UpdateProductRequest;
import co.istard.productapisimpledemo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductRestController {
    private final ProductService productService;

//    @GetMapping
//    public List<ProductResponese> getProducts() {
//        return productService.findAllProducts();
//    }

    //find product by id
    //localhosh:8080/api/v1/product/1001
    @GetMapping("/{id}")
    public ProductResponese getProductByID(@PathVariable Integer id) {
        return productService.findProductById(id);
    }

    @PostMapping
    public ProductResponese createProduct(@RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }

    // PATCH localhost:8080/api/v1/products
    // Content-Type JSON
    @PatchMapping("/{id}")
    public ProductResponese updateProduct(@PathVariable Integer id, @RequestBody UpdateProductRequest request) {
        return productService.updateProduct(id, request);
    }

    //    @GetMapping
//        public Page<ProductResponese> getProducts(Pageable pageable){
//            return productService.findAllProct(pageable);
//    }
    @GetMapping
    public Page<ProductResponese> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String keyword) {
        return productService.findAllProct(keyword,page,size);
    }
    }