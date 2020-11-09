package pharma.magazine.adapters.magazinedb.repository.base;

import org.springframework.data.repository.PagingAndSortingRepository;
import pharma.magazine.adapters.magazinedb.entity.Store;

import java.util.List;
import java.util.Optional;

public interface StoreRepositoryJpa extends PagingAndSortingRepository<Store, Long> {
    Optional<Store> findById(Long id);
    Optional<Store> findByName(String name);
    Store save(Store store);
    List<Store> findAll();
}
