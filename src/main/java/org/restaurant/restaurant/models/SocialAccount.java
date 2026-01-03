package org.restaurant.restaurant.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "social_accounts")
@Data
public class SocialAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "provider", nullable = false, length = 20)
    private String provider;
    @Column(name = "provider_id", length = 50)
    private Long providerId;
    private String name;
    private String email;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
