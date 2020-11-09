package pharma.magazine.adapters.magazinedb.repository.implementation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import pharma.magazine.domain.model.ProductModel;
import pharma.magazine.domain.model.StaffModel;
import pharma.magazine.domain.model.StockModel;
import pharma.magazine.domain.model.StoreModel;
import pharma.magazine.domain.ports.service.ProductService;
import pharma.magazine.domain.ports.service.StaffService;
import pharma.magazine.domain.ports.service.StockService;
import pharma.magazine.domain.ports.service.StoreService;

import java.math.BigDecimal;
import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest
@EnableJpaRepositories("pharma.magazine")
public class StockServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private StockService stockService;

    @Autowired
    private StoreService storeService;

    @Test
    public void saveAndFindStockTest() throws InterruptedException {

        StaffModel staffModel;
        if(staffService.findAll().size()>0){
            staffModel = staffService.findAll().get(0);
        }
        else{
            staffModel = staffService.create(StaffModel.builder().id(1L).email("ab1c@pl").build());
        }

        ProductModel productModel = ProductModel.builder()
                .brand("brand")
                .active(true)
                .name("nameAnother")
                .price(new BigDecimal(10))
                .build();

        ProductModel created = productService.create(productModel, staffModel);
        Optional<ProductModel> productCreated = productService.findById(created.getId());
        assert productCreated.isPresent();
        ProductModel productModel1 = productCreated.get();
        assert productModel1.getCreatedBy().getId()==staffModel.getId();

        StoreModel storeModel;
        if(storeService.findAll().size()>0){
            storeModel = storeService.findAll().get(0);
        }
        else{
            storeModel = storeService.create(StoreModel.builder().name("Store").build());
        }
        long quantity = 119L;
        StockModel stockModel = StockModel.builder().quantity(quantity).storeId(storeModel.getId()).productId(productModel1.getId()).build();
        StockModel stockModelCreated = stockService.create(stockModel, staffModel);
        Optional<StockModel> byIds = stockService.findByIds(stockModelCreated.getStoreId(), stockModelCreated.getProductId());
        assert byIds.isPresent();
        assert byIds.get().getQuantity() == quantity;
    }
}
