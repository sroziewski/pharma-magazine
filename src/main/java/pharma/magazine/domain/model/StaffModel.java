package pharma.magazine.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffModel {
    protected  Long id;
    protected  String firstName;
    protected  String lastName;
    protected  String email;
    protected  String phone;
    protected  String password;
    protected  StoreModel storeModel;
    protected  Boolean active;
}
