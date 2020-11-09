package pharma.magazine.domain.ports;

import java.util.List;
import java.util.Optional;

public interface GeneralRepository<T> {
    T save(T t);
    Optional<T> findById(Long id);
    List<T> findAll();
}
