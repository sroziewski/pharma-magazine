package pharma.magazine.adapters.magazinedb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import pharma.magazine.domain.model.StoreModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Optional;

@Entity(name = "stores")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private String email;
    private String street;
    private String city;
    private String zipCode;

    public StoreModel toModel(){
        return StoreModel.builder()
            .id(id)
            .name(name)
            .phone(phone)
            .email(email)
            .street(street)
            .city(city)
            .zipCode(zipCode)
            .build();
    }

    public static Store of(StoreModel storeModel){
        return Store.builder()
            .id(Optional.ofNullable(storeModel.getId()).orElse(null))
            .name(storeModel.getName())
            .phone(storeModel.getPhone())
            .email(storeModel.getEmail())
            .street(storeModel.getStreet())
            .city(storeModel.getCity())
            .zipCode(storeModel.getZipCode())
            .build();
    }
}
