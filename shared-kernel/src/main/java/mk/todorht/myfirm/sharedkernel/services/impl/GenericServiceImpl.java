package mk.todorht.myfirm.sharedkernel.services.impl;

import com.sun.xml.bind.v2.model.core.ID;
import lombok.AllArgsConstructor;
import mk.todorht.myfirm.sharedkernel.repository.GenericRepository;
import mk.todorht.myfirm.sharedkernel.services.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class GenericServiceImpl<T, ID extends Serializable> implements GenericService<T,ID> {

    private final GenericRepository<T, ID> repository;

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }
}
