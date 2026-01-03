package org.restaurant.restaurant.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CategoryDTO {
    @NotEmpty(message = "Category name can't be empty")
    private String name;
}
