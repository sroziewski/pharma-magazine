package pharma.magazine.domain.ports.service;

import org.springframework.stereotype.Component;
import pharma.magazine.domain.model.StaffModel;
import pharma.magazine.domain.model.StockModel;
import pharma.magazine.domain.ports.IProductRepository;
import pharma.magazine.domain.ports.IStockRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class StockService implements ICrudService<StockModel> {

    private final IStockRepository stockRepository;
    private final IProductRepository productRepository;

    public StockService(IStockRepository stockRepository, IProductRepository productRepository) {
        this.stockRepository = stockRepository;
        this.productRepository = productRepository;
    }

    public StockModel create(StockModel stockModel, StaffModel currentUser){
        productRepository.findById(stockModel.getProductId()).orElseThrow(() -> new ProductNotFoundException(stockModel.getProductId()));
        stockModel.setCreatedAt(LocalDateTime.now());
        stockModel.setCreatedBy(currentUser);
        return stockRepository.save(stockModel);
    }

    @Override
    public Optional<StockModel> update(StockModel stockModel, StaffModel currentUser) {
        productRepository.findById(stockModel.getProductId()).orElseThrow(() -> new ProductNotFoundException(stockModel.getProductId()));
        Optional<StockModel> stockOpt = stockRepository.findByIds(stockModel.getStoreId(), stockModel.getProductId());
        return stockOpt.map(updatingStock->{
            updatingStock.setUpdatedAt(LocalDateTime.now());
            updatingStock.setUpdatedBy(currentUser);
            updatingStock.setQuantity(stockModel.getQuantity());
            return stockRepository.save(updatingStock);
        });
    }

    public Optional<StockModel> findByIds(Long storeId, Long productId) {
        return stockRepository.findByIds(storeId, productId);
    }

    public List<StockModel> findAllByProduct(Long productId) {
        return stockRepository.findAllByProduct(productId);
    }

    public List<StockModel> findAllByStore(Long storeId) {
        return stockRepository.findAllByStore(storeId);
    }

    public List<StockModel> findAll(){
        return stockRepository.findAll();
    }

}
