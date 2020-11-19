package pharma.magazine.adapters.magazinedb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import pharma.magazine.domain.model.StockModel;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity(name = "stocks")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Stock {

    @EmbeddedId
    private StockId stockId;

    private Long quantity;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ManyToOne()
    private Staff createdBy;
    @ManyToOne()
    private Staff updatedBy;

    public StockModel toModel(){
        return StockModel.builder()
            .productId(stockId.getProductId())
            .storeId(stockId.getStoreId())
            .updatedAt(updatedAt)
            .updatedBy(updatedBy==null?null:updatedBy.toModel())
            .createdAt(createdAt)
            .createdBy(createdBy==null?null:createdBy.toModel())
            .quantity(quantity)
            .build();
    }

    public static Stock of(StockModel stockModel) {
        return Stock.builder()
            .quantity(stockModel.getQuantity())
            .createdBy(Staff.of(stockModel.getCreatedBy()))
            .createdAt(stockModel.getCreatedAt())
            .updatedBy(Staff.of(stockModel.getUpdatedBy()))
            .updatedAt(stockModel.getUpdatedAt())
            .stockId(StockId.of(stockModel.getProductId(), stockModel.getStoreId()))
            .build();
    }
}
