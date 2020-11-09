package pharma.magazine.adapters.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import pharma.magazine.domain.model.ProductModel;
import pharma.magazine.domain.model.StaffModel;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    public static StaffDto toDto(StaffModel staffModel) {
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
