package pharma.magazine.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockModel implements ICrud<String> {
    protected  Long productId;
    protected  Long storeId;
    protected  Long quantity;
    protected  LocalDateTime createdAt;
    protected  LocalDateTime updatedAt;
    protected  StaffModel createdBy;
    protected  StaffModel updatedBy;

    @Override
    public String getId() {
        return String.format("(%d, %d)", storeId, productId);
    }
}
