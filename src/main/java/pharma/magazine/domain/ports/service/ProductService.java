package pharma.magazine.domain.ports.service;

import org.springframework.stereotype.Component;
import pharma.magazine.domain.model.ProductModel;
import pharma.magazine.domain.model.StaffModel;
import pharma.magazine.domain.ports.IProductRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class ProductService implements ICrudService<ProductModel> {

    private final IProductRepository productRepository;

    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductModel create(ProductModel productModel, StaffModel currentUser){
        productModel.setCreatedAt(LocalDateTime.now());
        productModel.setCreatedBy(currentUser);
        return productRepository.save(productModel);
    }

    @Override
    public Optional<ProductModel> update(ProductModel productModel, StaffModel currentUser) {
        ProductModel persisted = productRepository.findById(productModel.getId()).orElseThrow(() -> new ProductNotFoundException(productModel.getId()));
        productModel.setActive(Optional.ofNullable(productModel.getActive()).orElse(persisted.getActive()));
        productModel.setBrand(Optional.ofNullable(productModel.getBrand()).orElse(persisted.getBrand()));
        productModel.setName(Optional.ofNullable(productModel.getName()).orElse(persisted.getName()));
        productModel.setPrice(Optional.ofNullable(productModel.getPrice()).orElse(persisted.getPrice()));
        productModel.setUpdatedAt(LocalDateTime.now());
        productModel.setUpdatedBy(currentUser);
        productModel.setCreatedAt(persisted.getCreatedAt());
        productModel.setCreatedBy(persisted.getCreatedBy());
        return Optional.of(productRepository.save(productModel));
    }

    public Optional<ProductModel> findById(Long id){
        return productRepository.findById(id);
    }

    public Optional<ProductModel> getByName(String name){
        return productRepository.findByName(name);
    }

    public List<ProductModel> findAll(){
        return productRepository.findAll();
    }


}
