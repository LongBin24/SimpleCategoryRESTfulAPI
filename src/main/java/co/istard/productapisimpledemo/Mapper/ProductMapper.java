package co.istard.productapisimpledemo.Mapper;

import co.istard.productapisimpledemo.dto.ProductRequest;
import co.istard.productapisimpledemo.dto.ProductResponese;
import co.istard.productapisimpledemo.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponese mapToResponse(Product request);
    Product mapToProduct(ProductRequest request);
}

