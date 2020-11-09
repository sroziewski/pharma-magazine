package pharma.magazine.adapters.magazinedb.repository.base;

import org.springframework.data.repository.PagingAndSortingRepository;
import pharma.magazine.adapters.magazinedb.entity.Stock;

import java.util.List;
import java.util.Optional;

public interface StockRepositoryJpa extends PagingAndSortingRepository<Stock, Long> {
    Optional<Stock> findByStockId_ProductIdAndStockId_StoreId(Long productId, Long storeId);
    List<Stock> findAllByStockId_ProductId(Long productId);
    List<Stock> findAllByStockId_StoreId(Long storeId);
    List<Stock> findAll();
}
