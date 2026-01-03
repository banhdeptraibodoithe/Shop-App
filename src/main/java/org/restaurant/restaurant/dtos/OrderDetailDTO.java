package org.restaurant.restaurant.dtos;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class OrderDetailDTO {
    @Min(value = 1, message = "Order ID must be > 0")
    private Long orderId;
    @Min(value = 1, message = "Product ID must be > 0")
    private Long productId;
    @Min(value = 0, message = "Price must be >= 0")
    private Float price;
    @Min(value = 1, message = "Quantity must be > 0")
    private int quantity;
    @Min(value = 0, message = "Total_money must be >= 0")
    private Float totalMoney;
    private String color;
}
