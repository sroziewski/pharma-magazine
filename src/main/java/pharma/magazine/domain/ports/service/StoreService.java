package pharma.magazine.domain.ports.service;

import org.springframework.stereotype.Component;
import pharma.magazine.domain.model.StoreModel;
import pharma.magazine.domain.ports.IStoreRepository;

import java.util.List;
import java.util.Optional;

@Component
public class StoreService {

    private final IStoreRepository storeRepository;

    public StoreService(IStoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public StoreModel create(StoreModel storeModel){
       return storeRepository.save(storeModel);
    }

    public Optional<StoreModel> findById(Long id){
        return storeRepository.findById(id);
    }

    public List<StoreModel> findAll(){
        return storeRepository.findAll();
    }
}
