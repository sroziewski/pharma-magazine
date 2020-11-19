package pharma.magazine.adapters.magazinedb.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import pharma.magazine.domain.model.ProductModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    private String brand;
    private BigDecimal price;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ManyToOne()
    private Staff createdBy;
    @ManyToOne()
    private Staff updatedBy;

    public ProductModel toModel(){
        return ProductModel.builder()
            .id(id)
            .active(active)
            .brand(brand)
            .name(name)
            .price(price)
            .createdAt(createdAt)
            .updatedAt(updatedAt)
            .updatedBy(updatedBy==null?null:updatedBy.toModel())
            .createdBy(createdBy==null?null:createdBy.toModel())
            .build();
    }

    public static Product of(ProductModel productModel){
        return Product.builder()
            .id(Optional.ofNullable(productModel.getId()).orElse(null))
            .name(productModel.getName())
            .brand(productModel.getBrand())
            .price(productModel.getPrice())
            .active(productModel.getActive())
            .createdAt(productModel.getCreatedAt())
            .updatedAt(productModel.getUpdatedAt())
            .createdBy(Staff.of(productModel.getCreatedBy()))
            .updatedBy(Staff.of(productModel.getUpdatedBy()))
            .build();
    }

}
