package pharma.magazine.domain.ports;

import org.springframework.stereotype.Repository;
import pharma.magazine.domain.model.StaffModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface IStaffRepository extends GeneralRepository<StaffModel> {
    Optional<StaffModel> findByLastName(String lastName);
    Optional<StaffModel> findByFirstName(String firstName);
    Optional<StaffModel> findByEmail(String email);
    List<StaffModel> findAllByStoreId(Long id);
}
