package pharma.magazine.adapters.magazinedb.repository.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pharma.magazine.adapters.magazinedb.entity.Staff;
import pharma.magazine.adapters.magazinedb.repository.base.StaffRepositoryJpa;
import pharma.magazine.domain.model.StaffModel;
import pharma.magazine.domain.ports.IStaffRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class StaffRepository implements IStaffRepository {

    @Autowired
    private StaffRepositoryJpa staffRepositoryJpa;

    @Override
    public Optional<StaffModel> findByLastName(String lastName) {
        return staffRepositoryJpa.findByLastName(lastName).map(Staff::toModel);
    }

    @Override
    public Optional<StaffModel> findByFirstName(String firstName) {
        return staffRepositoryJpa.findByFirstName(firstName).map(Staff::toModel);
    }

    @Override
    public Optional<StaffModel> findByEmail(String email) {
        return staffRepositoryJpa.findByEmail(email).map(Staff::toModel);
    }

    @Override
    public List<StaffModel> findAllByStoreId(Long id) {
        return staffRepositoryJpa.findAllByStoreId(id).stream().map(Staff::toModel).collect(Collectors.toList());
    }

    @Override
    public StaffModel save(StaffModel staffModel) {
        return staffRepositoryJpa.save(Staff.of(staffModel)).toModel();
    }

    @Override
    public Optional<StaffModel> findById(Long id) {
        return staffRepositoryJpa.findById(id).map(Staff::toModel);
    }

    @Override
    public List<StaffModel> findAll() {
        return staffRepositoryJpa.findAll().stream().map(Staff::toModel).collect(Collectors.toList());
    }
}
