package pharma.magazine.domain.ports.service;

import pharma.magazine.domain.model.StaffModel;

import java.util.List;
import java.util.Optional;

public interface ICrudService<T> {
    T create(T model, StaffModel currentUser);
    Optional<T> update(T model, StaffModel currentUser);
    List<T> findAll();
}
