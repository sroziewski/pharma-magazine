package pharma.magazine.domain.ports;

import org.springframework.stereotype.Repository;
import pharma.magazine.domain.model.StockModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface IStockRepository extends GeneralRepository<StockModel> {
    Optional<StockModel> findByIds(Long storeId, Long productId);
    List<StockModel> findAllByProduct(Long productId);
    List<StockModel> findAllByStore(Long storeId);
}
