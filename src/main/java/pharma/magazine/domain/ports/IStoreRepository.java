package pharma.magazine.domain.ports;

import org.springframework.stereotype.Repository;
import pharma.magazine.domain.model.StoreModel;

import java.util.Optional;

@Repository
public interface IStoreRepository extends GeneralRepository<StoreModel> {
    Optional<StoreModel> findByName(String name);
}
