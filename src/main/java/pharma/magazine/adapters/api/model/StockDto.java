package pharma.magazine.adapters.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pharma.magazine.domain.model.StockModel;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
@ToString(callSuper=true, includeFieldNames=true)
public class StockDto extends StockModel implements ResponsePayload<StockModel> {

    @Override
    public StockModel toModel() {
        return StockModel.builder()
            .storeId(storeId)
            .productId(productId)
            .quantity(quantity)
            .createdAt(createdAt)
            .createdBy(createdBy)
            .updatedAt(updatedAt)
            .updatedBy(updatedBy)
            .build();
    }

    public static StockDto of(StockModel stockModel){
        return StockDto.builder()
            .quantity(stockModel.getQuantity())
            .storeId(stockModel.getStoreId())
            .productId(stockModel.getProductId())
            .createdBy(StaffDto.of(stockModel.getCreatedBy()))
            .createdAt(stockModel.getCreatedAt())
            .updatedBy(StaffDto.of(stockModel.getUpdatedBy()))
            .updatedAt(stockModel.getUpdatedAt())
            .build();
    }
}
