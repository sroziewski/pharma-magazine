package pharma.magazine.adapters.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import pharma.magazine.domain.model.ProductModel;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
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

}
