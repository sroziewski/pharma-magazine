package pharma.magazine.adapters.magazinedb.repository.implementation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import pharma.magazine.domain.model.ProductModel;
import pharma.magazine.domain.model.StaffModel;
import pharma.magazine.domain.ports.service.ProductService;
import pharma.magazine.domain.ports.service.StaffService;

import java.math.BigDecimal;
import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest
@EnableJpaRepositories("pharma.magazine")
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private StaffService staffService;

    @Test
    public void saveAndFindProductTest(){
        StaffModel staffModel;
        if(staffService.findAll().size()>0){
            staffModel = staffService.findAll().get(0);
        }
        else{
            staffModel = staffService.create(StaffModel.builder().id(1L).email("abc@pl").build());
        }
        ProductModel productModel = ProductModel.builder()
                .brand("brand")
                .active(true)
                .name("name")
                .price(new BigDecimal(10))
                .build();

        ProductModel created = productService.create(productModel, staffModel);
        Optional<ProductModel> byId = productService.findById(created.getId());
        assert byId.isPresent();
        ProductModel productModel1 = byId.get();
        assert productModel1.getCreatedBy().getId()==staffModel.getId();
    }
}
