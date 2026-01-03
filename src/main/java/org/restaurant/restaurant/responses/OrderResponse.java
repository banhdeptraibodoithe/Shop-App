package org.restaurant.restaurant.responses;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse extends BaseResponse{
    private Long id;
    private Long userId;
    private String fullName;
    private String phoneNumber;
    private String address;
    private String note;
    private Date orderDate;
    private String orderStatus;
    private Float totalMoney;
    private String shippingMethod;
    private String shippingAddress;
    private LocalDate shippingDate;
    private String trackingNumber;
    private String paymentMethod;
}
