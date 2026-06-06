package co.istard.productapisimpledemo.dto;


public record UpdateProductRequest(
        String name,
        String description,
        Float price
) { }
