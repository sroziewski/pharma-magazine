package pharma.magazine.adapters.magazinedb.repository.base;

import org.springframework.data.repository.PagingAndSortingRepository;
import pharma.magazine.adapters.magazinedb.entity.Staff;
import pharma.magazine.adapters.magazinedb.entity.Store;

import java.util.List;
import java.util.Optional;

public interface StaffRepositoryJpa extends PagingAndSortingRepository<Staff, Long> {
    Optional<Staff> findById(Long id);
    Optional<Staff> findByLastName(String lastName);
    Optional<Staff> findByFirstName(String firstName);
    Optional<Staff> findByEmail(String email);
    Staff save(Staff staff);
    List<Staff> findAll();
    List<Staff> findAllByStoreId(Long id);
}
