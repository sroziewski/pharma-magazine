package pharma.magazine.adapters.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import pharma.magazine.domain.model.StockModel;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockDto extends StockModel implements ResponsePayload<StockModel> {

    private StaffDto createdByDto;
    private StaffDto updatedByDto;

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
}
