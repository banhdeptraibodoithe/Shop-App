package org.restaurant.restaurant.models;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    private Float price;
    @Column(name = "url", length = 500)
    private String url;
    private String description;
    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;
}
