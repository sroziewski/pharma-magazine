package pharma.magazine.adapters.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pharma.magazine.domain.model.ProductModel;
import pharma.magazine.domain.model.StaffModel;

@Data
@NoArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true, includeFieldNames=true)
public class StaffDto extends StaffModel implements ResponsePayload<StaffModel> {

    @Override
    public StaffModel toModel() {
        return StaffModel.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .active(active)
                .email(email)
                .phone(phone)
                .build();
    }

    public static StaffDto of(StaffModel staffModel) {
        if(staffModel==null)return null;
        StaffDto staffDto = new StaffDto();
        staffDto.setActive(staffModel.getActive());
        staffDto.setEmail(staffModel.getEmail());
        staffDto.setId(staffModel.getId());
        staffDto.setFirstName(staffModel.getFirstName());
        staffDto.setLastName(staffModel.getLastName());
        staffDto.setPhone(staffModel.getPhone());
        return staffDto;
    }
}
