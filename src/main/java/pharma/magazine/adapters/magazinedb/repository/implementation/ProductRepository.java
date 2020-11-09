package pharma.magazine.adapters.magazinedb.repository.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pharma.magazine.adapters.magazinedb.entity.Product;
import pharma.magazine.adapters.magazinedb.repository.base.ProductRepositoryJpa;
import pharma.magazine.domain.model.ProductModel;
import pharma.magazine.domain.ports.IProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductRepository implements IProductRepository {

    @Autowired
    private ProductRepositoryJpa productRepositoryJpa;

    @Override
    public Optional<ProductModel> findByName(String name) {
        return productRepositoryJpa.findByName(name).map(Product::toModel);
    }

    @Override
    public ProductModel update(ProductModel productModel) {
        return productRepositoryJpa.save(Product.of(productModel)).toModel();
    }

    @Override
    public ProductModel save(ProductModel productModel) {
        return productRepositoryJpa.save(Product.of(productModel)).toModel();
    }

    @Override
    public Optional<ProductModel> findById(Long id) {
        return productRepositoryJpa.findById(id).map(Product::toModel);
    }

    @Override
    public List<ProductModel> findAll() {
        return productRepositoryJpa.findAll().stream().map(Product::toModel).collect(Collectors.toList());
    }
}
