package pharma.magazine.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel implements ICrud<Long> {
    protected  Long id;
    protected  String name;
    protected  BigDecimal price;
    protected  String brand;
    protected  LocalDateTime createdAt;
    protected  LocalDateTime updatedAt;
    protected  StaffModel createdBy;
    protected  StaffModel updatedBy;
    protected  Boolean active;
}
