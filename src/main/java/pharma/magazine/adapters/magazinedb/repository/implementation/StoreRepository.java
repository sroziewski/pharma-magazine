package pharma.magazine.adapters.magazinedb.repository.implementation;

import org.springframework.stereotype.Repository;
import pharma.magazine.adapters.magazinedb.entity.Store;
import pharma.magazine.adapters.magazinedb.repository.base.StoreRepositoryJpa;
import pharma.magazine.domain.model.StoreModel;
import pharma.magazine.domain.ports.IStoreRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class StoreRepository implements IStoreRepository {

    private final StoreRepositoryJpa storeRepositoryJpa;

    public StoreRepository(StoreRepositoryJpa storeRepositoryJpa) {
        this.storeRepositoryJpa = storeRepositoryJpa;
    }

    @Override
    public StoreModel save(StoreModel storeModel) {
        return storeRepositoryJpa.save(Store.of(storeModel)).toModel();
    }

    @Override
    public Optional<StoreModel> findById(Long id) {
        return storeRepositoryJpa.findById(id).map(Store::toModel);
    }

    @Override
    public List<StoreModel> findAll() {
        return storeRepositoryJpa.findAll().stream().map(Store::toModel).collect(Collectors.toList());
    }

    @Override
    public Optional<StoreModel> findByName(String name) {
        return storeRepositoryJpa.findByName(name).map(Store::toModel);
    }
}
