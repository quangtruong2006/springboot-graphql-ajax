package vn.iotstar.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;
import java.io.Serializable;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "product_name", columnDefinition = "NVARCHAR(200)")
    private String productName;

    private Double unitPrice;
    private Double discount;
    
    @Column(columnDefinition = "NVARCHAR(1000)")
    private String description;

    private Integer quantity;
    private Short status;
    private String images;
    
    private Timestamp createDate;

    // Quan hệ N-1: Nhiều Product thuộc về 1 Category
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}