package pharma.magazine.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockModel implements ICrud {
    protected  Long productId;
    protected  Long storeId;
    protected  Long quantity;
    protected  LocalDateTime createdAt;
    protected  LocalDateTime updatedAt;
    protected  StaffModel createdBy;
    protected  StaffModel updatedBy;

    @Override
    public Long getId() {
        return null;
    }
}
