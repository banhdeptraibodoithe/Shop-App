package org.restaurant.restaurant.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
    @NotEmpty(message = "Name of product can't be empty!")
    @Size(min = 3, max = 100, message = "Size name must be between 3 to 100 characters!")
    private String name;
    @Min(value = 0, message = "Price can't be negative!")
    private Float price;
    private String url;
    private String description;
    private Long categoryId;
}
