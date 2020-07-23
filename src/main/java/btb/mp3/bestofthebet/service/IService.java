package btb.mp3.bestofthebet.service;

import java.util.List;
import java.util.Optional;

public interface IService<T> {
    List<T> findAll();
    Optional<T> findById(Long id);
    void save(T model);
    void delete(Long id);
}
