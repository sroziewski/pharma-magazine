package pharma.magazine.domain.model;

import java.time.LocalDateTime;

public interface ICrud {
    void setCreatedBy(StaffModel staffModel);
    void setUpdatedBy(StaffModel staffModel);
    void setUpdatedAt(LocalDateTime localDateTime);
    void setCreatedAt(LocalDateTime localDateTime);
    Long getId();
}
