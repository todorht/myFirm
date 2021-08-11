package mk.todorht.myfirm.sharedkernel.services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenericService<T, ID extends Serializable> {
    List<T> findAll();
    T save(T entity);
    Optional<T> findById(ID id);
    void delete(T entity);
    void deleteById(ID id);
}
