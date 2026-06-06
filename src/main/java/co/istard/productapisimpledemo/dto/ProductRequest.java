package co.istard.productapisimpledemo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ProductRequest(
        @NotBlank(message = "name is required")
        String name,
        @NotBlank(message = "description is required")
        String description,
        @NotBlank(message = "price is required")
        @Positive(message = "price must be positive")
        Float price
) {
}
