package pharma.magazine.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class StaffModel {
    protected  Long id;
    protected  String firstName;
    protected  String lastName;
    protected  String email;
    protected  String phone;
    @ToString.Exclude
    protected  String password;
    protected  StoreModel storeModel;
    protected  Boolean active;
}
