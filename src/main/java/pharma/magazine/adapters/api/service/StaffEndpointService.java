package pharma.magazine.adapters.api.service;

import org.springframework.stereotype.Component;
import pharma.magazine.adapters.api.model.StaffDto;
import pharma.magazine.domain.model.StaffModel;

@Component
public class StaffEndpointService implements IDtoConverter<StaffModel, StaffDto>{

    @Override
    public StaffDto converter(StaffModel staffModel) {
        if(staffModel==null){
            return null;
        }
        StaffDto staffDto = new StaffDto();
        staffDto.setPhone(staffModel.getPhone());
        staffDto.setLastName(staffModel.getLastName());
        staffDto.setFirstName(staffModel.getFirstName());
        staffDto.setId(staffModel.getId());
        staffDto.setEmail(staffModel.getEmail());
        return staffDto;
    }
}
