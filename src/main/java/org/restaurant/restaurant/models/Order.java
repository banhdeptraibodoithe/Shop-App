package org.restaurant.restaurant.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "full_name", length = 100)
    private String fullName;
    @Column(name = "email", length = 150)
    private String email;
    @Column(name = "phone_number", nullable = false, length = 10)
    private String phoneNumber;
    private String address;
    private String note;
    @Column(name = "order_date")
    private Date orderDate;
    @Column(name = "order_status")
    private String orderStatus;
    @Column(name = "total_money")
    private Float totalMoney;
    @Column(name = "shipping_method", length = 100)
    private String shippingMethod;
    @Column(name = "shipping_address", length = 200)
    private String shippingAddress;
    @Column(name = "shipping_date")
    private LocalDate shippingDate;
    @Column(name = "tracking_number", length = 100)
    private String trackingNumber;
    @Column(name = "payment_method", length = 100)
    private String paymentMethod;
    @Column(name = "is_active")
    private boolean isActive;
}
