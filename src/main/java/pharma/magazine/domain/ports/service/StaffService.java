package pharma.magazine.domain.ports.service;

import org.springframework.stereotype.Component;
import pharma.magazine.domain.model.StaffModel;
import pharma.magazine.domain.ports.IStaffRepository;

import java.util.List;
import java.util.Optional;

@Component
public class StaffService {

    private final IStaffRepository staffRepository;

    public StaffService(IStaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public StaffModel create(StaffModel staffModel){
        return staffRepository.save(staffModel);
    }

    public Optional<StaffModel> findById(Long id){
        return staffRepository.findById(id);
    }

    public Optional<StaffModel> findByEmail(String email){
        return staffRepository.findByEmail(email);
    }

    public List<StaffModel> findAll(){
        return staffRepository.findAll();
    }
}
