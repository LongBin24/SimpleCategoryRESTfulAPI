package co.istard.productapisimpledemo.dto;

import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
    public record CategoryResponse(
            @Size(min = 1 , max = 100)
        String name,
        @Size(min = 1 , max = 225)
        String description
) {
}
