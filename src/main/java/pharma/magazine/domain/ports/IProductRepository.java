package pharma.magazine.domain.ports;

import org.springframework.stereotype.Repository;
import pharma.magazine.domain.model.ProductModel;

import java.util.Optional;

@Repository
public interface IProductRepository extends GeneralRepository<ProductModel> {
    Optional<ProductModel> findByName(String name);
    ProductModel update(ProductModel productModel);
}
