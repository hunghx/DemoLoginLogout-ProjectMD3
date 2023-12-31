package ra.service;

import java.util.List;

public interface IGenericSevice<T, E> {
    List<T> findAll();
    void save(T t);
    void delete(E e);
    T findById(E e);
}
