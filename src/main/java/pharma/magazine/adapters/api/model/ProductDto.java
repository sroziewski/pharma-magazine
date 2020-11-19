package pharma.magazine.adapters.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pharma.magazine.domain.model.ProductModel;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper=true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto extends ProductModel implements ResponsePayload<ProductModel> {

    private StaffDto createdByDto;
    private StaffDto updatedByDto;

    @Override
    public ProductModel toModel() {
        return ProductModel.builder()
            .id(id)
            .name(name)
            .price(price)
            .active(active)
            .brand(brand)
            .createdAt(createdAt)
            .createdBy(createdBy)
            .updatedAt(updatedAt)
            .updatedBy(updatedBy)
            .build();
    }


    public static ProductDto of(ProductModel productModel){
        return ProductDto.builder()
            .id(productModel.getId())
            .name(productModel.getName())
            .brand(productModel.getBrand())
            .price(productModel.getPrice())
            .active(productModel.getActive())
            .createdBy(productModel.getCreatedBy())
            .createdAt(productModel.getCreatedAt())
            .updatedAt(productModel.getCreatedAt())
            .updatedBy(productModel.getUpdatedBy())
            .build();
    }

}
