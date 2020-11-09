package pharma.magazine.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreModel {
    private  Long id;
    private  String name;
    private  String email;
    private  String phone;
    private  String street;
    private  String city;
    private  String zipCode;
}
