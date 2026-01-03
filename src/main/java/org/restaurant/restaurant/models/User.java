package org.restaurant.restaurant.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name", length = 100)
    private String fullName;
    @Column(name = "phone_number", length = 10, nullable = false)
    private String phoneNumber;
    private String address;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "password", length = 100, nullable = false)
    private String password;
    @Column(name = "is_active")
    private boolean isActive;
    @Column(name = "facebook_account_id")
    private Long facebookAccountId;
    @Column(name = "google_account_id")
    private Long googleAccountId;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
