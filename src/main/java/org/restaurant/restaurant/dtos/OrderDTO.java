package org.restaurant.restaurant.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class OrderDTO {
    @Min(value = 1, message = "User's ID must be > 0")
    private Long userId;
    private String fullName;
    private String email;
    private String address;
    private String note;
    @NotBlank(message = "Phone number can't empty!")
    @Size(min = 10, message = "Phone number length size = 10 characters!")
    private String phoneNumber;
    @Min(value = 0, message = "Total_money must be >= 0")
    private Float totalMoney;
    private String shippingMethod;
    private String shippingAddress;
    private LocalDate shippingDate;
    private String paymentMethod;
}
