package pharma.magazine.adapters.magazinedb.repository.implementation;

import org.springframework.stereotype.Repository;
import pharma.magazine.adapters.magazinedb.entity.Stock;
import pharma.magazine.adapters.magazinedb.repository.base.StockRepositoryJpa;
import pharma.magazine.domain.model.StockModel;
import pharma.magazine.domain.ports.IStockRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class StockRepository implements IStockRepository {

    private final StockRepositoryJpa stockRepositoryJpa;

    public StockRepository(StockRepositoryJpa stockRepositoryJpa) {
        this.stockRepositoryJpa = stockRepositoryJpa;
    }

    @Override
    public Optional<StockModel> findByIds(Long storeId, Long productId) {
        return stockRepositoryJpa.findByStockId_ProductIdAndStockId_StoreId(productId, storeId).map(Stock::toModel);
    }

    @Override
    public List<StockModel> findAllByProduct(Long productId) {
        return stockRepositoryJpa.findAllByStockId_ProductId(productId).stream().map(Stock::toModel).collect(Collectors.toList());
    }

    @Override
    public List<StockModel> findAllByStore(Long storeId) {
        return stockRepositoryJpa.findAllByStockId_StoreId(storeId).stream().map(Stock::toModel).collect(Collectors.toList());
    }

    @Override
    public StockModel save(StockModel stockModel) {
        return stockRepositoryJpa.save(Stock.of(stockModel)).toModel();
    }

    @Override
    public Optional<StockModel> findById(Long id) {
        return stockRepositoryJpa.findById(id).map(Stock::toModel);
    }

    @Override
    public List<StockModel> findAll() {
        return stockRepositoryJpa.findAll().stream().map(Stock::toModel).collect(Collectors.toList());
    }
}
