package pharma.magazine.adapters.magazinedb.repository.base;

import org.springframework.data.repository.PagingAndSortingRepository;
import pharma.magazine.adapters.magazinedb.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryJpa extends PagingAndSortingRepository<Product, Long> {
    Optional<Product> findById(Long id);
    Optional<Product> findByName(String name);
    Product save(Product product);
    List<Product> findAll();
}
