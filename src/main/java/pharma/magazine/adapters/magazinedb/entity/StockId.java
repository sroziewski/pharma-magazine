package pharma.magazine.adapters.magazinedb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class StockId implements Serializable {
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "store_id")
    private Long storeId;

    public static StockId of(Long productId, Long storeId){
        return StockId.builder()
            .productId(productId)
            .storeId(storeId)
            .build();
    }

    @Override
    public String toString(){
        return String.format("(%d, %d)", storeId, productId);
    }
}
