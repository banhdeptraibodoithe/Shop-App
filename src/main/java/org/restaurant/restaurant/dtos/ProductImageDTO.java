package org.restaurant.restaurant.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductImageDTO {
    @Min(value = 1, message = "Product's Id must >= 1!")
    private Long productId;
    @Size(min = 5, max = 300, message = "Image's name")
    private String imageUrl;
}
